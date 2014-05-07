// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model;

import java.io.Serializable;

/**
 * Specifies the contract of the builder pattern for an entity.
 *
 * @param <T> The entity which the builder will create a new instance.
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface DomainObjectBuilder<T extends DomainObject> extends Serializable {

    /**
     * Creates a instances of a given DomainObject.
     *
     * @return a new instance.
     */
    T build();
}
