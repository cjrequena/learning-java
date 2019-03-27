package com.sample.java8.interfaces;

/**
 * Default Methods for Interfaces
 *
 * Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the default keyword. This feature is also known as Extension Methods.
 *
 */
public interface DefaultMethodOnInterface {

  double calculate(int a);

  default double sqrt(int a) {
    return Math.sqrt(a);
  }

}
