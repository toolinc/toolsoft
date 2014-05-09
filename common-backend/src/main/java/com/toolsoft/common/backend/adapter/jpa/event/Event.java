// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.adapter.jpa.event;

import com.toolsoft.common.backend.AssertionConcern;
import com.toolsoft.common.backend.domain.event.DomainEvent;
import com.toolsoft.common.backend.domain.model.DomainObjectBuilder;
import com.toolsoft.common.backend.domain.model.DomainObjectConcurrencySafe;
import com.toolsoft.common.backend.serializer.DomainEventSerializer;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Stores a domain event.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
@Entity
@Table(name = "Event")
public class Event extends DomainObjectConcurrencySafe {

    @NotNull
    @Id
    @Column(name = "idEvent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Column(name = "type", length = 500, nullable = false)
    private String type;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "occurredOn", nullable = false)
    private Date occurredOn;

    @NotNull
    @Column(name = "eventId", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "message", length = 5000, nullable = false)
    private String message;

    @Deprecated
    public Event() {
    }

    private Event(Builder builder) {
        setType(builder.type);
        setOccurredOn(builder.occurredOn);
        setEventId(builder.eventId);
        setMessage(builder.message);
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        assertArgumentNotNull(id, "Id cannot be null.");
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        assertArgumentNotNull(type, "Type cannot be null.");
        this.type = type;
    }

    public Date getOccurredOn() {
        return newDate(occurredOn);
    }

    public void setOccurredOn(Date occurredOn) {
        assertArgumentNotNull(occurredOn, "Date cannot be null.");
        this.occurredOn = newDate(occurredOn);
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        assertArgumentNotNull(eventId, "EventId cannot be null.");
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        assertArgumentNotNull(message, "Message cannot be null.");
        this.message = message;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;
        try {
            domainEventClass = (Class<T>) Class.forName(getType());
        } catch (Exception e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }
        T domainEvent = DomainEventSerializer.instance()
                .deserialize(getMessage(), domainEventClass);
        return domainEvent;
    }

    /**
     * Builder of {@link Event} instances.
     *
     * @author Edgar Rico (edgar.martinez.rico@gmail.com)
     */
    public static class Builder extends AssertionConcern implements DomainObjectBuilder<Event> {

        private Date occurredOn;
        private int eventId;
        private String type;
        private String message;

        private Builder() {
        }

        public Builder setMessage(DomainEvent domainEvent) {
            assertArgumentNotNull(domainEvent, "DomainEvent cannot be null.");
            assertArgumentNotNull(domainEvent.getOccurredOn(), "OccurredOn cannot be null.");
            type = domainEvent.getClass().getName();
            message = DomainEventSerializer.instance().serialize(domainEvent);
            occurredOn = domainEvent.getOccurredOn();
            eventId = domainEvent.getEventVersion();
            assertArgumentNotNull(message, "Message cannot be null.");
            return this;
        }

        /**
         * Creates a instances of {@link Event} given the specified
         * characteristics on the {@link Event.Builder}.
         *
         * @return a new instance {@link Event}
         */
        @Override
        public Event build() {
            return new Event(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of {@link Event.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
