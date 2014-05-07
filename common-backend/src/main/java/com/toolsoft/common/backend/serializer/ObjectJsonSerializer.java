// Copyright 2014 Tool Inc.

package com.toolsoft.common.backend.serializer;

import java.lang.reflect.Type;

/**
 * Specifies a JSON object serializer and serializer.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class ObjectJsonSerializer extends AbstractJsonSerializer {

    private static ObjectJsonSerializer eventSerializer;

    public static synchronized ObjectJsonSerializer instance() {
        if (ObjectJsonSerializer.eventSerializer == null) {
            ObjectJsonSerializer.eventSerializer = new ObjectJsonSerializer();
        }
        return ObjectJsonSerializer.eventSerializer;
    }

    public ObjectJsonSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public ObjectJsonSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public <T extends Object> T deserialize(String aSerialization, final Class<T> aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);
        return domainEvent;
    }

    public <T extends Object> T deserialize(String aSerialization, final Type aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);
        return domainEvent;
    }

    public String serialize(Object anObject) {
        String serialization = this.gson().toJson(anObject);
        return serialization;
    }

    private ObjectJsonSerializer() {
        this(false, false);
    }
}
