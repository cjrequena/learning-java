package com.cjrequena.sample.pattern.eventsourcing;

public class AccountBalanceException extends RuntimeServiceException {
  public AccountBalanceException(String message) {
    super(message);
  }
}
