package com.sample.java8.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Streams {

  public void filter(List<Integer> collection) {
    collection.stream().filter(number -> isPrime(number)).forEach(System.out::println);
  }

  public void sort(List<Integer> collection, boolean asc) {
    if(asc)
      collection.stream().sorted().forEach(System.out::println);
    else
      collection.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
  }

  public void map(List<Integer> collection){
    collection.stream().map(number -> number + 10).forEach(System.out::println);
  }

  public void limit(List<Integer> collection, int limit){
    collection.stream().limit(limit).forEach(System.out::println);
  }

  public  void count(List<Integer> collection){
    System.out.println(collection.stream().count());
  }

  public void printParallelStream(List<Integer> collection) {
    collection.parallelStream().forEach(System.out::println);
  }

  /**
   * Checks if a integer is a prime number
   * @param number
   * @return
   */
  private static boolean isPrime(int number) {
    IntPredicate isDivisible = index -> number % index == 0;
    return number > 1 && IntStream.range(2, number - 1).noneMatch(isDivisible);
  }



}


  
  
  
