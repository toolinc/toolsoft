// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model;

import com.toolsoft.common.backend.AssertionConcern;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.MappedSuperclass;

/**
 * Represents an identified domain object which is {@link java.io.Serializable}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@MappedSuperclass
public abstract class DomainObject extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DomainObject() {
    }

    public abstract BigInteger getId();

    public abstract void setId(BigInteger id);

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final DomainObject other = (DomainObject) obj;
        return Objects.equals(getId(), other.getId());
    }
}
