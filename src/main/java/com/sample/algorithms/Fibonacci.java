package com.sample.algorithms;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Fibonacci series is a series of natural numbers where the next number is equivalent to the sum of the previous two numbers
 * like fn = fn-1 + fn-2. The first two numbers of the Fibonacci series are always 1, 1. In this Java program example for the
 * Fibonacci series, we create a function to calculate Fibonacci number and then print those numbers on Java console.
 */
@Log4j2
public class Fibonacci {

  public static void main(String arg[]) {
    fibonacciWithLambda(21).stream().forEach(log::info);
  }

  /**
   * Java 8 / Lambda approach to generate fibonacci series.
   * Fibonacci always start as classic (e.g. 0, 1, 1, 2, 3, 5)
   * @param series Number of how many fibonacci number should be generated
   * @return List holding resulting fibonacci number.
   */
  public static List<Integer> fibonacciWithLambda(int series) {
    return Stream.iterate(new int[] {0, 1}, s -> new int[] {s[1], s[0] + s[1]})
      .limit(series)
      .map(n -> n[0])
      .collect(toList());
  }
}
