package com.cjrequena.sample.algorithms;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author cjrequena
 */
@Log4j2
class FibonacciTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void fibonacciWithLambda() {
        log.info(Fibonacci.fibonacciWithLambda(9));
    }

    @Test
    void fibonacciRecursive() {
        int i = 0;
        int n = 9;
        int[] result = new int[n + 1];
        while (i <= n) {
            result[i] = Fibonacci.fibonacciRecursive(i);
            i++;
        }
        log.info(Arrays.toString(result));
    }

    /**
     *
     *
     *
     *
     */
    @Test
    void fibonacciDynamicEg1() {
        int i = 0;
        int n = 9;
        int[] result = new int[n + 1];
        while (i <= n) {
            result[i] = Fibonacci.fibonacciDynamicEg1(i, null);
            i++;
        }
        log.info(Arrays.toString(result));
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
     * 2. Print the value of arr[N].
     *
     * Time Complexity: O(N)
     * Auxiliary Space: O(N)
     */
    @Test
    void fibonacciDynamicEg2() {
        int i = 0;
        int n = 9;
        int[] result = new int[n + 1];
        while (i <= n) {
            result[i] = Fibonacci.fibonacciDynamicEg2(i);
            i++;
        }
        log.info(Arrays.toString(result));
    }
}
