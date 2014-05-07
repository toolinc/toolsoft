// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model.enablement;

import com.toolsoft.common.backend.domain.model.DomainObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Specifies the behavior of entities that needs to be active between specific range of dates.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@MappedSuperclass
public abstract class RangeDateDomainObject extends DomainObject implements EnablementRangeDate {

    @Temporal(TemporalType.DATE)
    @Column(name = "effectiveStart")
    private Date effectiveStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "effectiveEnd")
    private Date effectiveEnd;

    @Column(name = "active")
    private boolean enable;

    @Override
    public Date getEffectiveStart() {
        return effectiveStart;
    }

    @Override
    public void setEffectiveStart(Date effectiveStart) {
        this.effectiveStart = effectiveStart;
    }

    @Override
    public Date getEffectiveEnd() {
        return effectiveEnd;
    }

    @Override
    public void setEffectiveEnd(Date effectiveEnd) {
        this.effectiveEnd = effectiveEnd;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
