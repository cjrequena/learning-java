package com.cjrequena.sample.pattern.eventsourcing;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class WithdrawAccountCommand extends Command {

  private final WithdrawVO data;

  @Builder
  public WithdrawAccountCommand(UUID aggregateId, @NotNull WithdrawVO data) {
    super(aggregateId, AggregateType.ACCOUNT_AGGREGATE.getType());
    this.data = data;
  }

}
