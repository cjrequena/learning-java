package com.cjrequena.sample.pattern.eventsourcing;

public class AmountException extends DomainRuntimeException {
  public AmountException(String message) {
    super(message);
  }
}
