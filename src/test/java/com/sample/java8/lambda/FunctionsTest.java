package com.sample.java8.lambda;

import org.junit.Test;

import java.util.Stack;

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

  @Test
  public void addTest() {
    assertEquals(Functions.add.apply(Double.valueOf(2), Double.valueOf(2)), Double.valueOf(4));
  }

  @Test
  public void multiplyTest() {
    assertEquals(Functions.multiply.apply(Double.valueOf(3), Double.valueOf(3)), Double.valueOf(9));
  }

  @Test
  public void applyOperationToOperandsTest() {
    Stack<Double> stack = new Stack<>();
    stack.push(Double.valueOf(3));
    stack.push(Double.valueOf(3));
    assertEquals(Functions.applyOperationToOperands(stack, Functions.add),  Double.valueOf(6));
    stack.push(Double.valueOf(3));
    stack.push(Double.valueOf(3));
    assertEquals(Functions.applyOperationToOperands(stack, Functions.multiply),  Double.valueOf(9));

  }

}
