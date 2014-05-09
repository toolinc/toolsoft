// Copyright 2014 Tool Inc.

package com.toolsoft.common.tenant;

import com.toolsoft.common.backend.AssertionConcern;
import com.toolsoft.common.tenant.domain.Tenant;

import java.io.Serializable;
import java.util.Objects;

/**
 * A context in which tenancy can be defined.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class TenancyContext extends AssertionConcern implements Serializable {

    private final Tenant tenant;

    public TenancyContext(Tenant tenant) {
        assertArgumentNotNull(tenant, "The tenant cannot be null.");
        this.tenant = tenant;
    }

    public Tenant getTenant() {
        return tenant;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTenant());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TenancyContext other = (TenancyContext) obj;
        return Objects.equals(getTenant(), other.getTenant());
    }

    @Override
    public String toString() {
        return "TenancyContext {" + "tenant = " + tenant + " }";
    }
}
