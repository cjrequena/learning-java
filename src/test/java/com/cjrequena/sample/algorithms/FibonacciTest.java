package com.cjrequena.sample.algorithms;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cjrequena
 */
@Log4j2
class FibonacciTest {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void fibonacciWithLambda() {
    Fibonacci.fibonacciWithLambda(21).stream().forEach(log::info);
    fail();
  }
}
