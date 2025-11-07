package com.cjrequena.sample.pattern.eventsourcing;

public class AccountBalanceException extends DomainRuntimeException {
  public AccountBalanceException(String message) {
    super(message);
  }
}
