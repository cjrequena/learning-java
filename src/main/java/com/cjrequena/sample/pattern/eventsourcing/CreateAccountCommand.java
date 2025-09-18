package com.cjrequena.sample.pattern.eventsourcing;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class CreateAccountCommand extends Command {

  private final AccountVO data;

  @Builder
  public CreateAccountCommand(AccountVO data) {
    super(UUID.randomUUID(), AggregateType.ACCOUNT_AGGREGATE.getType());
    this.data = AccountVO
      .builder()
      .id(getAggregateId())
      .owner(data.owner())
      .email(data.email())
      .balance(data.balance())
      .isActive(data.isActive())
      .build();
  }

}
