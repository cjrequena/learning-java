package com.sample.java8.lambda;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuppliersTest {

  @Test
  public void suppliersTest(){
    assertNotNull(Suppliers.hashMapSupplier.get());
    assertNotNull(Suppliers.concurrentHashMapSupplier.get());
    assertNotNull(Suppliers.arrayListSupplier.get());
  }
}
