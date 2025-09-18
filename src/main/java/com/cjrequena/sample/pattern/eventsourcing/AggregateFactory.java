package com.cjrequena.sample.pattern.eventsourcing;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.UUID;

@RequiredArgsConstructor
public class AggregateFactory {

    @SneakyThrows(ReflectiveOperationException.class)
    @SuppressWarnings("unchecked")
    public <T extends Aggregate> T newInstance( Class<? extends Aggregate> aggregateClass, UUID aggregateId) {
        var constructor = aggregateClass.getDeclaredConstructor(UUID.class, Long.TYPE);
        return (T) constructor.newInstance(aggregateId, 0);
    }
}
