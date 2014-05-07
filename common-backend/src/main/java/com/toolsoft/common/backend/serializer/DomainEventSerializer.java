// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.serializer;

import com.toolsoft.common.backend.domain.event.DomainEvent;

/**
 * Defines a {@link com.toolsoft.common.backend.domain.event.DomainEvent} serializer.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class DomainEventSerializer extends AbstractJsonSerializer {

    private static DomainEventSerializer domainEventSerializer;

    private DomainEventSerializer() {
        this(false, false);
    }

    protected DomainEventSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    protected DomainEventSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(DomainEvent aDomainEvent) {
        String serialization = this.gson().toJson(aDomainEvent);
        return serialization;
    }

    public <T extends DomainEvent> T deserialize(String aSerialization, final Class<T> aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);
        return domainEvent;
    }

    public static synchronized DomainEventSerializer instance() {
        if (DomainEventSerializer.domainEventSerializer == null) {
            DomainEventSerializer.domainEventSerializer = new DomainEventSerializer();
        }
        return DomainEventSerializer.domainEventSerializer;
    }
}
