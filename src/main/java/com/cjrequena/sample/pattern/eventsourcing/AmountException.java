package com.cjrequena.sample.pattern.eventsourcing;

public class AmountException extends RuntimeServiceException {
  public AmountException(String message) {
    super(message);
  }
}
