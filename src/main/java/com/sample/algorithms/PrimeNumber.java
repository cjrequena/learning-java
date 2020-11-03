package com.sample.algorithms;

public class PrimeNumber {

  public static void main(String arg[]) {
    //System.out.println(isPrimeNumberWay1(1));
    printPrimeNumbers(1000);
  }

  /**
   *
   * @param number
   * @return
   */
  public static boolean isPrimeNumberWay1(int number) {
    if (number < 2) {
      return false;
    }
    if (number == 2) {
      return true;
    }
    if (number % 2 == 0) {
      return false;
    }
    int sqrt = (int) Math.sqrt(number) + 1;
    for (int i = 3; i < sqrt; i += 2) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   *
   * @param number
   * @return
   */
  public static boolean isPrimeNumberWay2(int number) {
    if (number < 2) {
      return false;
    }
    int sqrt = (int) Math.sqrt(number) + 1;
    for (int i = 2; i < sqrt; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void printPrimeNumbers(int limit) {
    for (int i = 0; i <= limit; i++) {
      if (isPrimeNumberWay1(i)) {
        System.out.println(i + " ");
      }
    }
  }
}
