package com.sample.algorithms.fibonacci;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Fibonacci {

  public static void main(String arg[]){
    System.out.println(fibonacci(4));
  }

  /*
   * Java program for Fibonacci number using recursion.
   * This program uses tail recursion to calculate Fibonacci number
   * for a given number
   * @return Fibonacci number
   */
  public static int fibonacci(int number){
    if(number == 1 || number == 2){
      return 1;
    }
    return fibonacci(number-1) + fibonacci(number -2); //tail recursion
  }

  public static void fibonacciSeries(int size){
    String fibonacciSeries = "";
    for (int i = 1; i <= size; i++) {
      fibonacciSeries = fibonacciSeries + fibonacci(i) +" ";
    }
    System.out.println(fibonacciSeries);
  }
}
