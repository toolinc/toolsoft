// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model.enablement;

/**
 * Specifies the behavior of whether or not an entity is active.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface Enablement {

    /**
     * Retrieves the enable status of an instance.
     *
     * @return the status
     */
    boolean isEnable();

    /**
     * Specifies the enable status.
     *
     * @param enable the status
     */
    void setEnable(boolean enable);
}
