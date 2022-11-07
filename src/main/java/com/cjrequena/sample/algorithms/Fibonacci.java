package com.cjrequena.sample.algorithms;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Fibonacci series is a series of natural numbers where the next number is equivalent to the sum of the previous two numbers
 * like fn = fn-1 + fn-2. The first two numbers of the Fibonacci series are always 1, 1. In this Java program example for the
 * Fibonacci series, we create a function to calculate Fibonacci number and then print those numbers on Java console.
 */
@Log4j2
public class Fibonacci {

    /**
     * Java 8 / Lambda approach to generate fibonacci series.
     * Fibonacci always start as classic (e.g. 0, 1, 1, 2, 3, 5)
     *
     * @param series Number of how many fibonacci number should be generated
     * @return List holding resulting fibonacci number.
     */
    public static List<Integer> fibonacciWithLambda(int series) {
        return Stream.iterate(new int[]{0, 1}, s -> new int[]{s[1], s[0] + s[1]})
                .limit(series)
                .map(n -> n[0])
                .collect(toList());
    }

    /**
     * Using Recursion: Since Fibonacci Number is the summation of the two previous numbers. We can use recursion as per the following condition:
     * <p>
     * 1. Get the number whose Fibonacci series needs to be calculated.
     * 2. Recursively iterate from value N to 1:
     * - Base case: If the value called recursively is less than 1, the return 1 the function.
     * - Recursive call: If the base case is not met, then recursively call for previous two value as:
     * recursive_function(N – 1) + recursive_function(N – 2);
     * <p>
     * - Return statement: At each recursive call(except the base case), return the recursive function for the previous two value as:
     * recursive_function(N – 1) + recursive_function(N – 2);
     * <p>
     * Time Complexity: O(2N)
     * Auxiliary Space: O(1)
     *
     * @param n
     * @return
     */
    public static int fibonacciRecursive(int n) {
        if (n == 0 || n == 1)
            return 1;
        return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
    }

    public static int fibonacciDynamicEg1(int n, Integer[] memo) {
        if (memo == null || memo.length == 0) {
            memo = new Integer[n + 2];
            memo[0] = 1;
            memo[1] = 1;
        }
        if (memo[n] != null)
            return memo[n];
        memo[n] = fibonacciDynamicEg1(n - 1, memo) + fibonacciDynamicEg1(n - 2, memo);
        return memo[n];
    }

    /**
     *
     * Using Dynamic Programming: We can avoid the repeated work done in method 2 by storing the Fibonacci numbers calculated so far. Below are the steps:
     *
     * 1. Create an array arr[] of size N.
     * 2. Initialize arr[0] = 0, arr[1] = 1.
     * 3. Iterate over [2, N] and update the array arr[] as:
     *    arr[i] = arr[i – 2] + arr[i – 1]
     *
     * 4. Print the value of arr[N].
     *
     * Time Complexity: O(N)
     * Auxiliary Space: O(N)
     */
    public static int fibonacciDynamicEg2(int n) {
        Integer[] memo = new Integer[n + 2];
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

}
