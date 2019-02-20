package com.sample.java8.lambda;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PredicatesTest {

  @Test
  public void isGreaterThan100Test() {
    assertTrue(Predicates.isGreaterThan100.test(101));
    assertTrue(Predicates.isGreaterThan100.negate().test(99));
  }

  @Test
  public void isNullTest() {
    Object obj = null;
    assertTrue(Predicates.isNull.test(null));
    assertTrue(Predicates.isNull.test(obj));
    obj = new Object();
    assertTrue(Predicates.isNull.negate().test(obj));
    Boolean b = Boolean.TRUE;
    assertTrue(Predicates.isNull.negate().test(b));
  }

  @Test
  public void isNonNullTest() {
    Object obj = null;
    assertTrue(Predicates.isNotNull.negate().test(null));
    assertTrue(Predicates.isNotNull.negate().test(obj));
    obj = new Object();
    assertTrue(Predicates.isNotNull.test(obj));
    Boolean b = Boolean.TRUE;
    assertTrue(Predicates.isNotNull.test(b));
  }

  @Test
  public void isStrngEmptyTest() {
    String emptyText="";
    String nonEmptyText="NON EMPTY";
    assertTrue(Predicates.isStringEmpty.test(emptyText));
    assertTrue(Predicates.isStringEmpty.negate().test(nonEmptyText));
  }

  @Test
  public void isStrngNonEmptyTest() {
    String emptyText="";
    String nonEmptyText="NON EMPTY";
    assertTrue(Predicates.isStringNotEmpty.test(nonEmptyText));
    assertTrue(Predicates.isStringNotEmpty.negate().test(emptyText));
  }
}
