package com.sample.algorithms;

/**
 * A prime number (or a prime) is a natural number greater than 1 that is not a product of two smaller natural numbers. A natural number
 * greater than 1 that is not prime is called a composite number.
 *
 * A simple but slow method of checking the primality of a given number n, called trial division, tests whether n is a multiple of any
 * integer between 2 and sqrt(n).
 *
 * Faster algorithms include the Miller–Rabin primality test, which is fast but has a small chance of error, and the AKS primality test,
 * which always produces the correct answer in polynomial time but is too slow to be practical.
 *
 */
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
   * @param limit
   */
  public static void printPrimeNumbers(int limit) {
    for (int i = 0; i <= limit; i++) {
      if (isPrimeNumberWay1(i)) {
        System.out.println(i + " ");
      }
    }
  }

}
