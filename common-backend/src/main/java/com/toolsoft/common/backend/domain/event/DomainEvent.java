// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.event;

import java.util.Date;

/**
 * Represents an event of a {@link com.toolsoft.common.backend.domain.model.DomainObject}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface DomainEvent {

    /**
     * Provides the event version.
     *
     * @return the version number
     */
    public int getEventVersion();

    /**
     * Provides the date in which the event was produced.
     *
     * @return the date of the event.
     */
    public Date getOccurredOn();
}
