// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.event;

/**
 * Specifies the contract for a {@link com.toolsoft.common.backend.domain.event.DomainEvent}
 * subscriber.
 *
 * @param <T> specifies the type of events that the subscriber handles
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public interface EventSubscriber<T extends DomainEvent> {

    /**
     * This call back will be invoke upon reception of a given
     * {@link com.toolsoft.common.backend.domain.event.DomainEvent} message.
     *
     * @param domainEvent the type of message that will be handle by the subscriber
     */
    void handle(final T domainEvent);
}
