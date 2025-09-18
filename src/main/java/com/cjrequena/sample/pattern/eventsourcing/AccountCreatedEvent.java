package com.cjrequena.sample.pattern.eventsourcing;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

@Getter
@SuperBuilder
@ToString(callSuper = true)
@Log4j2
public class AccountCreatedEvent extends Event {

  private final AccountVO data;

  @Nonnull
  @Override
  public String getEventType() {
    return EventType.ACCOUNT_CREATED_EVENT.getEventType();
  }

}
