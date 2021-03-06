// Copyright 2014 Tool Inc.

package com.toolsoft.common.tenant.domain;

import com.toolsoft.common.backend.AssertionConcern;

import java.io.Serializable;
import java.util.Objects;

/**
 * Define the characteristics of a tenant.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class Tenant extends AssertionConcern implements Serializable {

    private final Integer id;

    public Tenant(Integer id) {
        assertArgumentNotNull(id, "The tenant id cannot be null or empty.");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Tenant { id = [%d] }.", getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tenant other = (Tenant) obj;
        return Objects.equals(getId(), other.getId());
    }
}
