// Copyright 2014 Tool Inc.

package com.toolsoft.common.tenant.adapter.jpa;

import org.springframework.orm.jpa.support.SharedEntityManagerBean;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * A {@code EntityManager} that is tenant aware.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Singleton
public class SharedEntityManagerTenancyAware extends SharedEntityManagerBean {

    private final Provider<EntityManager> entityManagerProvider;

    @Inject
    public SharedEntityManagerTenancyAware(Provider<EntityManager> entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    protected EntityManager createEntityManager() throws IllegalStateException {
        return entityManagerProvider.get();
    }
}
