// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.adapter.jpa.event;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.toolsoft.common.backend.AssertionConcern;
import com.toolsoft.common.backend.domain.event.DomainEvent;
import com.toolsoft.common.backend.domain.repository.Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Jpa implementation for the {@link com.toolsoft.common.backend.adapter.jpa.event.Event}
 * repository.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class JpaEventRepository extends AssertionConcern {

    private final Repository<Event> repository;

    @Inject
    public JpaEventRepository(Repository<Event> repository) {
        assertArgumentNotNull(repository, "Repository cannot be null.");
        this.repository = repository;
    }

    /**
     * Stores a {@link com.toolsoft.common.backend.domain.event.DomainEvent} on the persistent
     * storage.
     *
     * @param domainEvent the event that will be stored
     * @return the new produced {@link com.toolsoft.common.backend.adapter.jpa.event.Event}
     */
    @Transactional(REQUIRED)
    public Event create(DomainEvent domainEvent) {
        Event event = Event.Builder.newBuilder()
                .setMessage(domainEvent)
                .build();
        repository.create(event);
        return event;
    }
}
