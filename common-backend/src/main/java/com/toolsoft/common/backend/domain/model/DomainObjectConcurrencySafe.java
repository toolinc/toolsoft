// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Represents a domain object that guarantees consistency through the use of optimistic locking.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@MappedSuperclass
public abstract class DomainObjectConcurrencySafe extends DomainObject {

    private static final long serialVersionUID = 1L;

    protected DomainObjectConcurrencySafe() {
    }

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int aVersion) {
        failWhenConcurrencyViolation(aVersion);
        version = aVersion;
    }

    protected void failWhenConcurrencyViolation(int aVersion) {
        assertArgumentEquals(version, aVersion,
                "Stale data detected. Entity was already modified.");
    }
}
