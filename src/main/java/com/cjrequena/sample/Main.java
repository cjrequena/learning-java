package com.cjrequena.sample;

public class Main {

  public static void main(String ... args){
    Test test = new Test();
    Integer number = 0;
    test.increment2(number);
    test.increment2(number);
    test.increment2(number);
    test.increment2(number);
    test.increment2(number);
    System.out.printf("Number: %s\n", number);

  }

  public static class Test {
    static int number = 0;
    public void increment() {
      number++;
      System.out.printf("Number: %s\n", number);
    }

    public void increment2(Integer number) {
      number = number + 1;
    }
  }
}


