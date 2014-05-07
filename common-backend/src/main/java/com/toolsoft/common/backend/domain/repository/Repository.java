// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.repository;

import com.toolsoft.common.backend.adapter.jpa.repository.QueryHelper;
import com.toolsoft.common.backend.domain.model.DomainObject;

import java.util.UUID;

import javax.persistence.EntityManager;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the Repository.
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface Repository<T extends DomainObject> {

    void create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID key);

    QueryHelper<T, T> newQueryHelper();

    EntityManager getEntityManager();
}
