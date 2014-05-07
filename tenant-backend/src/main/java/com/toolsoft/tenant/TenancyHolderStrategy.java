// Copyright 2014 Tool Inc.

package com.toolsoft.tenant;

import com.toolsoft.common.backend.AssertionConcern;

import javax.inject.Singleton;

/**
 * Associates a given {@link TenancyContext} with the current execution.
 * <p>
 * This class provides a series of static methods that delegate to an instance of
 * {@link TenancyStrategy}. The purpose of the class is to provide a convenient way
 * to specify the strategy that should be used for a given JVM. This is a JVM-wide setting, since
 * everything in this class is <code>static</code> to facilitate ease of use in calling code.
 * </p>
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Singleton
public class TenancyHolderStrategy extends AssertionConcern {

    private static TenancyStrategy strategy;

    /**
     * Explicitly clear the tenancy context.
     *
     */
    public static void clearCurrentTenancyContext() {
        strategy.clearCurrentTenancyContext();
    }

    /**
     * Obtain the current <code>TenancyContext</code>.
     *
     * @return the tenancy context (never <code>null</code>)
     */
    public static TenancyContext getCurrentTenancyContext() {
        return strategy.getCurrentTenancyContext();
    }

    /**
     * Associates a new <code>TenancyContext</code> with the current context of execution.
     *
     * @param context
     *            the new <code>TenancyContext</code> (may not be <code>null</code>)
     */

    public static void registerTenancyContext(TenancyContext context) {
        strategy.registerTenancyContext(context);
    }

    /**
     * Allows retrieval of the context strategy.
     *
     * @return the configured strategy for storing the tenancy context.
     */
    public static TenancyStrategy getStrategy() {
        return strategy;
    }

    /**
     * Set the context strategy.
     *
     * @param strategy
     *            the configured strategy for storing the tenancy context.
     */
    public static void setStrategy(TenancyStrategy strategy) {
        assertArgumentNotNull(strategy, "The tenant strategy cannot be null.");
        TenancyHolderStrategy.strategy = strategy;
    }
}
