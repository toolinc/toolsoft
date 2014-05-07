// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.adapter.jpa.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import com.toolsoft.common.backend.domain.model.DomainObject;
import com.toolsoft.common.backend.domain.repository.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * This class represents a Generic Data Access Object implementation.
 *
 * @param <T> Specifies the entity of the Repository.
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public abstract class JpaRepository<T extends DomainObject> implements Repository<T> {

    private final EntityManager em;
    private final Class<T> clazz;

    @Inject
    @SuppressWarnings("unchecked")
    public JpaRepository(EntityManager entityManager) {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Object clase = parameterizedType.getActualTypeArguments()[0];
        if (clase instanceof Class) {
            clazz = (Class<T>) clase;
        } else {
            throw new IllegalArgumentException("Unable to extract generic type information");
        }
        this.em = checkNotNull(entityManager);
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(UUID key) {
        return em.find(clazz, key);
    }

    @Override
    public QueryHelper<T, T> newQueryHelper() {
        return QueryHelper.newQuery(getEntityManager(), clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
