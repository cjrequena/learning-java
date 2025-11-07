package com.cjrequena.sample.pattern.eventsourcing;

import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Log4j2
public class Main {

  public static void main(String... args) {

    final Aggregate accountAggregate;
    try {
      Command createAccountCommand = CreateAccountCommand
        .builder()
        .data(AccountVO
          .builder()
          .owner("Pepe")
          .email("pepe@aggregate-account.com")
          .balance(new BigDecimal("999"))
          .isActive(true)
          .build()
        )
        .build();

      accountAggregate = AggregateFactory.newInstance(AccountAggregate.class, createAccountCommand.getAggregateId());
      accountAggregate.applyCommand(createAccountCommand);

      Command depositAccountCommand = DepositAccountCommand.builder()
        .aggregateId(accountAggregate.getAggregateId())
        .data(DepositVO
          .builder()
          .accountId(accountAggregate.getAggregateId())
          .amount(BigDecimal.valueOf(100))
          .build())
        .build();
      accountAggregate.applyCommand(depositAccountCommand);

      Command withdrawAccountCommand = WithdrawAccountCommand
        .builder()
        .aggregateId(accountAggregate.getAggregateId())
        .data(WithdrawVO
          .builder()
          .accountId(accountAggregate.getAggregateId())
          .amount(BigDecimal.valueOf(100))
          .build())
        .build();
      accountAggregate.applyCommand(withdrawAccountCommand);
      log.debug(accountAggregate);

      final Aggregate accountAggregate2 = AggregateFactory.newInstance(AccountAggregate.class, accountAggregate.aggregateId);
      accountAggregate2.reproduceFromEvents(accountAggregate.unconfirmedEventsPool);
      log.debug(accountAggregate2);
    } catch (Exception ex) {
      log.error("{}", ex.getMessage());
    }

  }
}
