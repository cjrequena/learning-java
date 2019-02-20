package com.sample.java8.lambda;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Predicates are boolean-valued functions of one argument. The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
 */
public class Predicates {

  public  static Predicate<Integer> isGreaterThan100 = (parameter) -> parameter > 100;

  public  static Predicate<Object> isNotNull = Objects::nonNull;

  public  static Predicate<Object> isNull = Objects::isNull;

  public  static Predicate<String> isStringEmpty = String::isEmpty;

  public  static Predicate<String> isStringNotEmpty = isStringEmpty.negate();
}
