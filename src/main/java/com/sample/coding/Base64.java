package com.sample.coding;

public class Base64 {

  public static String encode(String input) {
    return java.util.Base64.getEncoder().encodeToString(input.getBytes());
  }

  public static String decode(String input) {
    return new String(java.util.Base64.getDecoder().decode(input.getBytes()));
  }
}
