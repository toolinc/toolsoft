// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.domain.event;

import com.toolsoft.common.backend.AssertionConcern;

import java.util.Collection;

/**
 * Decorator of the {@link com.toolsoft.common.backend.domain.event.EventPublisher}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public abstract class DomainEventPublisher extends AssertionConcern {

    protected static DomainEventPublisher domainEventPublisher;

    protected abstract EventPublisher eventPublisher();

    public <T extends DomainEvent> void publish(T domainEvent) {
        eventPublisher().publish(domainEvent);
    }

    public <T extends DomainEvent> void publishAll(Collection<T> domainEvents) {
        eventPublisher().publishAll(domainEvents);
    }

    public static DomainEventPublisher instance() {
        return domainEventPublisher;
    }
}
