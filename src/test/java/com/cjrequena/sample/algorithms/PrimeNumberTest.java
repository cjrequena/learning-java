package com.cjrequena.sample.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

class PrimeNumberTest {

  @Test
  void testIsPrimeNumber() {
    assertTrue(PrimeNumber.isPrimeNumber(2));
    assertTrue(PrimeNumber.isPrimeNumber(3));
    assertTrue(PrimeNumber.isPrimeNumber(5));
    assertTrue(PrimeNumber.isPrimeNumber(7));
    assertFalse(PrimeNumber.isPrimeNumber(1));
    assertFalse(PrimeNumber.isPrimeNumber(4));
    assertFalse(PrimeNumber.isPrimeNumber(9));
    assertFalse(PrimeNumber.isPrimeNumber(15));
  }

  @Test
  void testGetPrimeNumbers() {
    List<Integer> expectedPrimesUpTo10 = Arrays.asList(2, 3, 5, 7);
    assertEquals(expectedPrimesUpTo10, PrimeNumber.getPrimeNumbers(10));

    List<Integer> expectedPrimesUpTo20 = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
    assertEquals(expectedPrimesUpTo20, PrimeNumber.getPrimeNumbers(20));

    List<Integer> expectedPrimesUpTo0 = List.of();
    assertEquals(expectedPrimesUpTo0, PrimeNumber.getPrimeNumbers(0));
  }
}
