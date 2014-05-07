// Copyright 2014 Tool Inc.

package com.toolsoft.tenant.adapter;

import com.toolsoft.common.backend.AssertionConcern;
import com.toolsoft.tenant.TenancyContext;
import com.toolsoft.tenant.TenancyStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A <code>ThreadLocal</code>-based implementation of {@link com.toolsoft.tenant.TenancyStrategy}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class ThreadLocalTenancyStrategy extends AssertionConcern implements TenancyStrategy {

    private static final ThreadLocal<TenancyContext> CACHE = new ThreadLocal<TenancyContext>();
    private final Logger logger = LoggerFactory.getLogger(ThreadLocalTenancyStrategy.class);

    @Override
    public void clearCurrentTenancyContext() {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Removing the current context [%s].", CACHE.get()));
        }
        CACHE.set(null);
    }

    @Override
    public TenancyContext getCurrentTenancyContext() {
        TenancyContext tenancyContext = CACHE.get();
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("The current context [%s].", tenancyContext));
        }
        return tenancyContext;
    }

    @Override
    public void registerTenancyContext(TenancyContext context) {
        assertArgumentNotNull(context, "The tenant context is null.");
        CACHE.set(context);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Registering [%s] for ThreadId [%d]", context,
                    Thread.currentThread().getId()));
        }
    }

    @Override
    public String toString() {
        return "Thread Local Tenancy Strategy.";
    }
}
