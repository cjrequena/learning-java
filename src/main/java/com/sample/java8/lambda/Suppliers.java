package com.sample.java8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
 */
public class Suppliers {

  public static Supplier<Map> hashMapSupplier = HashMap::new;
  public static Supplier<Map> concurrentHashMapSupplier = ConcurrentHashMap::new;
  public static Supplier<List> arrayListSupplier = ArrayList::new;
}
