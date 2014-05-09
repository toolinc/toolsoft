// Copyright 2014 Tool Inc.

package com.toolsoft.tenant.adapter.jpa;

import com.google.common.collect.ImmutableMap;
import com.toolsoft.common.backend.AssertionConcern;
import com.toolsoft.tenant.TenancyContext;
import com.toolsoft.tenant.TenancyHolderStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.util.CollectionUtils;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * A {@code EntityManager} provider that supports tenant capabilities.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Singleton
public class EntityManagerTenantAwareProvider extends AssertionConcern
        implements Provider<EntityManager> {

    private static final String MULTITENANT = "eclipselink.tenant-id";
    private final Logger logger = LoggerFactory.getLogger(Provider.class);
    private final EntityManagerFactory entityManagerFactory;
    private final Map<String, Object> jpaPropertyMap;

    @Inject
    public EntityManagerTenantAwareProvider(EntityManagerFactory entityManagerFactory,
            @Named("jpaProperties") Map<String, Object> jpaPropertyMap) {
        assertArgumentNotNull(entityManagerFactory, "Entity Manager Factory is null");
        assertArgumentNotNull(jpaPropertyMap, "Map cannot be null.");
        this.entityManagerFactory = entityManagerFactory;
        this.jpaPropertyMap = jpaPropertyMap;
    }

    @Override
    public EntityManager get() {
        logger.debug("About to create a new Entity Manager instance.");
        EntityManagerFactory emf = getEntityManagerFactory();
        if (emf instanceof EntityManagerFactoryInfo) {
            emf = ((EntityManagerFactoryInfo) emf).getNativeEntityManagerFactory();
        }
        Map<String, Object> properties = getJpaPropertyMap();
        TenancyContext tenancyContext = TenancyHolderStrategy.getCurrentTenancyContext();
        if (tenancyContext != null) {
            logger.debug("Found tenant [" + tenancyContext + ']');
            ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
            if (!CollectionUtils.isEmpty(properties)) {
                builder.putAll(getJpaPropertyMap());
            }
            builder.put(MULTITENANT, tenancyContext.getTenant().getId());
            properties = builder.build();
        }
        logger.debug("Creating a new Entity Manager from properties [" + properties + ']');
        return (!CollectionUtils.isEmpty(properties)
                ? emf.createEntityManager(properties) : emf.createEntityManager());
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public Map<String, Object> getJpaPropertyMap() {
        return jpaPropertyMap;
    }
}
