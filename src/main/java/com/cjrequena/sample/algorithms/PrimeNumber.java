package com.cjrequena.sample.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

  /**
   * Determines whether a given number is a prime number.
   *
   * A prime number is a number greater than 1 that has no divisors other than 1 and itself.
   * This implementation optimizes performance by checking divisibility up to the square root of the number.
   *
   * @param number The integer to check for primality.
   * @return {@code true} if the number is prime, {@code false} otherwise.
   */
  public static boolean isPrimeNumber(int number) {
    if (number < 2) {
      return false; // Numbers less than 2 are not prime
    }
    if (number == 2) {
      return true; // 2 is the only even prime number
    }
    if (number % 2 == 0) {
      return false; // Other even numbers are not prime
    }

    // Check divisibility from 3 up to sqrt(number), skipping even numbers
    return IntStream.rangeClosed(3, (int) Math.sqrt(number))
      .filter(i -> i % 2 != 0) // Skip even numbers
      .noneMatch(i -> number % i == 0);
  }


  /**
   * Determines whether a given number is a prime number.
   *
   * A prime number is a number greater than 1 that has no divisors other than 1 and itself.
   * This implementation optimizes performance by checking divisibility up to the square root of the number.
   *
   * @param number The integer to check for primality.
   * @return {@code true} if the number is prime, {@code false} otherwise.
   */
  public static boolean isPrimeNumberV2(int number) {
    if (number <= 1) return false;
    if (number == 2) return true;
    if (number % 2 == 0) return false;

    int sqrt = (int) Math.sqrt(number);
    for (int i = 3; i <= sqrt; i += 2) {
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
  public static List<Integer> getPrimeNumbers(int limit) {
    List<Integer> listPrimeNumbers = new ArrayList<>();
    for (int i = 0; i <= limit; i++) {
      if (isPrimeNumber(i)) {
        listPrimeNumbers.add(i);
      }
    }
    return listPrimeNumbers;
  }
}
