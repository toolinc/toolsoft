// Copyright 2014 Tool Inc.

package com.toolsoft.common.tenant.adapter.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * A {@code JpaTransactionManager} that is tenant aware.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Singleton
public class JpaTransactionManagerTenantAware extends JpaTransactionManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Provider<EntityManager> entityManagerProvider;

    @Inject
    public JpaTransactionManagerTenantAware(EntityManagerFactory entityManagerFactory,
                                            Provider<EntityManager> entityManagerProvider) {
        super(entityManagerFactory);
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    protected EntityManager createEntityManagerForTransaction() {
        return entityManagerProvider.get();
    }

}
