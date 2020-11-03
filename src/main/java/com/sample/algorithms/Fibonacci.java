package com.sample.algorithms;

import lombok.extern.log4j.Log4j2;

/**
 * Fibonacci series is a series of natural numbers where the next number is equivalent to the sum of the previous two numbers
 * like fn = fn-1 + fn-2. The first two numbers of the Fibonacci series are always 1, 1. In this Java program example for the
 * Fibonacci series, we create a function to calculate Fibonacci number and then print those numbers on Java console.
 */
@Log4j2
public class Fibonacci {

  public static void main(String arg[]) {
    //    System.out.println(fibonacciWithRecursion(4));
    //    System.out.println(fibonacciWithIteration(4));
    printFibonacciSeries(8);
  }

  /*
   * Java program for Fibonacci number using recursion.
   * This program uses tail recursion to calculate Fibonacci number
   * for a given position
   * @return Fibonacci number
   */
  public static int fibonacciWithRecursion(int position) {
    if (position == 1 || position == 2) {
      return 1;
    }
    return fibonacciWithRecursion(position - 1) + fibonacciWithRecursion(position - 2); //tail recursion
  }

  /*
   * Java program to calculate Fibonacci number using loop or Iteration.
   * This program uses iteration to calculate Fibonacci number
   * for a given position
   * @return Fibonacci number
   */
  public static int fibonacciWithIteration(int position) {
    if (position == 1 || position == 2) {
      return 1;
    }
    int fibonacci = 1, fibonacci1 = 1, fibonacci2 = 1;
    for (int i = 3; i <= position; i++) {
      //Fibonacci number is sum of previous two Fibonacci number
      fibonacci = fibonacci1 + fibonacci2;
      fibonacci1 = fibonacci2;
      fibonacci2 = fibonacci;
    }
    return fibonacci; //Fibonacci number
  }

  /**
   *
   * @param size
   */
  public static void printFibonacciSeries(int size) {
    String fibonacciSeries = "";
    for (int i = 1; i <= size; i++) {
      fibonacciSeries = fibonacciSeries + fibonacciWithRecursion(i) + " ";
    }
    System.out.println(fibonacciSeries);
  }
}
