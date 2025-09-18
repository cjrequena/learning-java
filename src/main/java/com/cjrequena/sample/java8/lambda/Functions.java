package com.cjrequena.sample.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Functions {

  /**
   *
   */
  public static Function<String, Integer> toInteger = Integer::valueOf;

  /**
   *
   */
  public static Function<String, String> backToString = toInteger.andThen(String::valueOf);

  /**
   *
   */
  public static BiFunction<Double, Double, Double> add = (operandA, operandB) -> operandA + operandB;

  /**
   *
   */
  public static BiFunction<Double, Double, Double> multiply = (operandA, operandB) -> operandA * operandB;

  /**
   *
   */
  public static BiFunction<Double, Double, Double> subtract = (operandA, operandB) -> operandA - operandB;

  /**
   *
   */
  public static BiFunction<Double, Double, Double> divide = (operandA, operandB) -> operandA / operandB;

  /**
   *
   */
  protected static Double applyOperationToOperands(Double operandA, Double operandB, BiFunction<Double, Double, Double> operation) {
    return operation.apply(operandA, operandB);
  }

  /**
   *
   */
  static Function<String, String> convertToCamelCase = search -> {
    Pattern bound = Pattern.compile("_[a-z]");
    StringBuffer searchStringBuffer = new StringBuffer(search.length());
    Matcher matcher = bound.matcher(search);
    while (matcher.find()) {
      matcher.appendReplacement(searchStringBuffer, matcher.group().toUpperCase().replace("_", ""));
    }
    return matcher.appendTail(searchStringBuffer).toString();
  };
}
