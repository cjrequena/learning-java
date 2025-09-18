package com.cjrequena.sample.pattern.eventsourcing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EventType {

  ACCOUNT_CREATED_EVENT(AccountCreatedEvent.class, AccountCreatedEvent.class.getName()),
  ACCOUNT_DEPOSITED_EVENT(AccountDepositedEvent.class, AccountDepositedEvent.class.getName()),
 ACCOUNT_WITHDRAWN_EVENT(AccountWithdrawnEvent.class, AccountWithdrawnEvent.class.getName());

  private final Class<? extends Event> eventClass;
  private final String eventType;

  //private final String value;
}
