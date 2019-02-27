package com.sample.java8.lambda;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionsTest {

  @Test
  public void backToStringTest() {
    assertEquals(Functions.backToString.apply("123"), "123");
  }

  @Test
  public void convertToCamelCaseTest() {
    assertEquals(Functions.convertToCamelCase.apply("hello_world"), "helloWorld");
  }

}
