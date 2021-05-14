package com.sample.java8.streams;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamsTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void filter() {

    Streams streams = new Streams();
    List<Integer> integers = new ArrayList<>();
    integers.add(0);
    integers.add(1);
    integers.add(2);
    integers.add(3);
    integers.add(4);
    integers.add(5);
    integers.add(6);
    integers.add(7);
    integers.add(8);
    integers.add(9);

    System.out.println("Print prime numbers");
    System.out.println("===================");
    streams.filter(integers);

    System.out.println("");
    System.out.println("Print numbers sorted desc");
    System.out.println("===================");
    streams.sort(integers, false);

    System.out.println("");
    System.out.println("Print numbers sorted asc");
    System.out.println("===================");
    streams.sort(integers, true);    System.out.println("");


    System.out.println("");
    System.out.println("Print numbers + 10");
    System.out.println("===================");
    streams.map(integers);

    System.out.println("");
    System.out.println("Print numbers from 0 to limit -1");
    System.out.println("===================");
    streams.limit(integers, 6);


    System.out.println("");
    System.out.println("Print the count of numbers");
    System.out.println("===================");
    streams.count(integers);


    System.out.println("");
    System.out.println("Print all numbers using parallelStream");
    System.out.println("===================");
    streams.printParallelStream(integers);
  }
}
