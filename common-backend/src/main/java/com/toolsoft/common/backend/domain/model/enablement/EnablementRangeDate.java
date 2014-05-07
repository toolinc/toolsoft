// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.model.enablement;

import java.util.Date;

/**
 * Specifies the behavior of the entities that need to keep the active between specific range of
 * dates.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface EnablementRangeDate extends Enablement {

    /**
     * Retrieves the date in which an instance of an object will become active.
     *
     * @return the specified date
     */
    Date getEffectiveStart();

    /**
     * Specifies the date in which an instance will be active.
     *
     * @param effectiveStart the date on which the instance will become active
     */
    void setEffectiveStart(Date effectiveStart);

    /**
     * Retrieves the date in which an instance of an object will expire.
     *
     * @return the specified date
     */
    Date getEffectiveEnd();

    /**
     * Specifies the date in which an instance will expire.
     *
     * @param effectiveEnd the date on which the instance will become expire
     */
    void setEffectiveEnd(Date effectiveEnd);
}
