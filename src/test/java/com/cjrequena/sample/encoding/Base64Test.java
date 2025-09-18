package com.cjrequena.sample.encoding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64Test {

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void encode() {
    assertEquals(Base64.encode("Hello::World"),  "SGVsbG86Oldvcmxk");
  }

  @Test
  void decode() {
    assertEquals(Base64.decode("SGVsbG86Oldvcmxk"),  "Hello::World");
  }
}
