package com.cjrequena.sample.pattern.eventsourcing;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
public class DepositAccountCommand extends Command {

  private final DepositVO data;

  @Builder
  public DepositAccountCommand(UUID aggregateId, @NotNull DepositVO data) {
    super(aggregateId, AggregateType.ACCOUNT_AGGREGATE.getType());
    this.data = data;
  }

}
