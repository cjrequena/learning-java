package com.cjrequena.sample.java8.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuppliersTest {

  @Test
  public void suppliersTest(){
    assertNotNull(Suppliers.hashMapSupplier.get());
    assertNotNull(Suppliers.concurrentHashMapSupplier.get());
    assertNotNull(Suppliers.arrayListSupplier.get());
  }
}
