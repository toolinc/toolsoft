// Copyright 2014 Tool Inc.

package com.toolsoft.common.tenant;

/**
 * A strategy for storing tenancy context information against an execution.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface TenancyStrategy {

    /**
     * Clears the current context.
     */
    void clearCurrentTenancyContext();

    /**
     * Obtains the current context.
     *
     * @return a context (never <code>null</code> - create a default implementation if necessary)
     */
    TenancyContext getCurrentTenancyContext();

    /**
     * Sets the current context.
     *
     * @param context to the new argument (should never be <code>null</code>, although
     *                implementations must check if <code>null</code> has been passed and throw an
     *                <code>IllegalArgumentException</code> in such cases).
     */
    void registerTenancyContext(TenancyContext context);
}
