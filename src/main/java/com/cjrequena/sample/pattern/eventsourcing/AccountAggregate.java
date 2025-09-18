package com.cjrequena.sample.pattern.eventsourcing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Log4j2
@Getter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountAggregate extends Aggregate {

  private AccountVO data;

  @Builder
  @JsonCreator
  public AccountAggregate(@NonNull @JsonProperty("aggregate_id") UUID aggregateId, @JsonProperty("aggregate_version") long aggregateVersion) {
    super(aggregateId, aggregateVersion);
    this.aggregateType = AggregateType.ACCOUNT_AGGREGATE.getType();
  }

  // ---------------------------
  // Commands
  // ---------------------------
  public void applyCommand(CreateAccountCommand command) throws JsonProcessingException {
    log.info("Applying command {}", command);
    applyUnconfirmedEvent(AccountCreatedEvent.builder()
      .aggregateId(command.getAggregateId())
      .aggregateVersion(getNextAggregateVersion())
      .dataContentType("application/json")
      .data(command.getData())
      //.dataBase64(JsonUtil.objectToJsonBase64(command.getData()))
      .build());
  }

  public void applyCommand(DepositAccountCommand command) throws JsonProcessingException {
    log.info("Applying command {}", command);
    applyUnconfirmedEvent(AccountDepositedEvent
      .builder()
      .aggregateId(command.getAggregateId())
      .aggregateVersion(getNextAggregateVersion())
      .dataContentType("application/json")
      .data(command.getData())
      //.dataBase64(JsonUtil.objectToJsonBase64(command.getData()))
      .build());
  }

  public void applyCommand(WithdrawAccountCommand command) throws JsonProcessingException {
    log.info("Applying command {}", command);
    applyUnconfirmedEvent(AccountWithdrawnEvent
      .builder()
      .aggregateId(command.getAggregateId())
      .aggregateVersion(getNextAggregateVersion())
      .dataContentType("application/json")
      .data(command.getData())
      //.dataBase64(JsonUtil.objectToJsonBase64(command.getData()))
      .build());
  }

  // ---------------------------
  // Events
  // ---------------------------
  public void applyEvent(AccountCreatedEvent event) {
    this.data = event.getData();
  }

  public void applyEvent(AccountDepositedEvent event) {
    final DepositVO depositVO = event.getData();
    if (depositVO.isAmountEqualOrLessThanZero()) {
      throw new AmountException("Invalid deposit amount: The amount must be greater than zero.");
    }
    this.data = this.data.addToBalanceAndClone(depositVO.amount());
  }

  public void applyEvent(AccountWithdrawnEvent event) {
    final WithdrawVO withdrawVO = event.getData();
    if (withdrawVO.isAmountEqualOrLessThanZero()) {
      throw new AmountException("Invalid withdraw amount: The amount must be greater than zero.");
    }
    this.data = this.data.subtractToBalanceAndClone(withdrawVO.amount());
  }

  @Nonnull
  @Override
  public String getAggregateType() {
    return AggregateType.ACCOUNT_AGGREGATE.getType();
  }
}
