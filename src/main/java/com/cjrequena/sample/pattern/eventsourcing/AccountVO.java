package com.cjrequena.sample.pattern.eventsourcing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Value Object representing an Account.
 * This record is immutable and contains the essential data for an account.
 */
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AccountVO(

  @NotNull
  UUID id,

  @NotBlank(message = "Account owner cannot be blank")
  String owner,

  @NotBlank(message = "Account email cannot be blank")
  String email,

  @NotNull
  @DecimalMin(value = "0.00", message = "Account balance cannot be negative")
  BigDecimal balance,

  @NotNull
  Boolean isActive

) implements Serializable {

  /**
   * Canonical constructor with validation and data normalization.
   */
  public AccountVO {
    // Auto-generate ID if null
    if (id == null) {
      id = UUID.randomUUID();
    }

    // Validate and normalize owner
    if (owner != null) {
      owner = owner.trim();
      if (owner.isEmpty()) {
        throw new IllegalArgumentException("Account owner cannot be empty");
      }
    }

    // Set default balance if null and normalize to 2 decimal places
    if (balance == null) {
      balance = BigDecimal.ZERO;
    } else {
      balance = balance.setScale(2, RoundingMode.HALF_UP);
      if (balance.compareTo(BigDecimal.ZERO) < 0) {
        throw new AccountBalanceException("Account balance cannot be negative");
      }
    }
  }

  /**
   * Creates a new AccountVO with updated balance.
   *
   * @param newBalance the new balance
   * @return a new AccountVO instance with the updated balance
   */
  public AccountVO cloneWithBalance(BigDecimal newBalance) {
    return AccountVO.builder()
      .id(this.id)
      .owner(this.owner)
      .email(this.email)
      .balance(newBalance)
      .isActive(this.isActive)
      .build();
  }

  /**
   * Creates a new AccountVO with updated owner.
   *
   * @param newOwner the new owner
   * @return a new AccountVO instance with the updated owner
   */
  public AccountVO cloneWithOwner(String newOwner) {
    return AccountVO.builder()
      .id(this.id)
      .owner(newOwner)
      .email(this.email)
      .balance(this.balance)
      .isActive(this.isActive)
      .build();
  }

  public AccountVO addToBalanceAndClone(BigDecimal amount) {
    final BigDecimal newBalance = this.balance.add(amount);
    return this.cloneWithBalance(newBalance);
  }

  public AccountVO subtractToBalanceAndClone(BigDecimal amount) {
    final BigDecimal newBalance = this.balance.subtract(amount);
    return this.cloneWithBalance(newBalance);
  }

  /**
   * Checks if the account has sufficient balance for a given amount.
   *
   * @param amount the amount to check
   * @return true if balance is sufficient, false otherwise
   */
  public boolean hasSufficientBalance(BigDecimal amount) {
    return balance != null && amount != null && balance.compareTo(amount) >= 0;
  }

  /**
   * Checks if the account balance is zero.
   *
   * @return true if balance is zero, false otherwise
   */
  public boolean isBalanceEqualToZero() {
    return balance != null && balance.compareTo(BigDecimal.ZERO) == 0;
  }

  /**
   * Checks if the account balance is less than zero.
   *
   * @return true if balance is zero, false otherwise
   */
  public boolean isBalanceLessThanZero() {
    return balance != null && balance.compareTo(BigDecimal.ZERO) < 0;
  }

  /**
   * Gets the balance as a formatted string for display purposes.
   *
   * @return formatted balance string
   */
  public String getFormattedBalance() {
    return balance != null ? balance.setScale(2, RoundingMode.HALF_UP).toString() : "0.00";
  }

  /**
   * Creates a new AccountVO for a new account with zero balance.
   *
   * @param owner the account owner
   * @return a new AccountVO with generated ID and zero balance
   */
  public static AccountVO createNewWith(String owner) {
    return AccountVO.builder()
      .owner(owner)
      .build(); // ID and balance will use defaults
  }

  /**
   * Creates a new AccountVO with specified balance.
   *
   * @param owner the account owner
   * @param initialBalance the initial balance
   * @return a new AccountVO with generated ID and specified balance
   */
  public static AccountVO createNewWith(String owner, BigDecimal initialBalance) {
    return AccountVO.builder()
      .owner(owner)
      .balance(initialBalance)
      .build(); // ID will use default
  }

}
