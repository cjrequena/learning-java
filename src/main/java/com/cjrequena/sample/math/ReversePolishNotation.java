package com.cjrequena.sample.math;

import java.util.*;
import java.util.function.BiFunction;

public class ReversePolishNotation {

  /**
   * Converts an infix expression to Reverse Polish Notation (RPN) and evaluates it.
   */
  public static Double applyInfixNotation(String input) {
    return applyRPN(convertInfixToRPN(input));
  }

  /**
   * Evaluates a mathematical expression written in Reverse Polish Notation (RPN).
   */
  public static Double applyRPN(String input) {
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException("Input cannot be null or empty.");
    }

    Deque<Double> operands = new ArrayDeque<>();

    for (String token : input.split(" ")) {
      switch (token) {
        case "+" -> applyOperationToOperands(operands, Double::sum);
        case "-" -> applyOperationToOperands(operands, (a, b) -> a - b);
        case "*" -> applyOperationToOperands(operands, (a, b) -> a * b);
        case "/" -> applyOperationToOperands(operands, (a, b) -> {
          if (b == 0)
            throw new ArithmeticException("Division by zero is not allowed.");
          return a / b;
        });
        default -> {
          try {
            operands.push(Double.parseDouble(token));
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid token in input: " + token);
          }
        }
      }
    }
    return operands.pop();
  }

  /**
   * Applies a binary operation to the top two elements of the operand stack.
   */
  private static void applyOperationToOperands(Deque<Double> operands, BiFunction<Double, Double, Double> operation) {
    if (operands.size() < 2) {
      throw new IllegalArgumentException("Insufficient operands for the operation.");
    }
    double operand2 = operands.pop();
    double operand1 = operands.pop();
    operands.push(operation.apply(operand1, operand2));
  }

  /**
   * Converts an infix expression to Reverse Polish Notation (RPN).
   */
  private static String convertInfixToRPN(String input) {
    // Remove all whitespace
    input = input.replaceAll("\\s+", "");

    // Tokenize the input
    List<String> tokens = new ArrayList<>();
    StringBuilder numberBuffer = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);

      if (Character.isDigit(c) || c == '.') {
        numberBuffer.append(c);
      } else if (c == '-' && (i == 0 || isOperator(input.charAt(i - 1)) || input.charAt(i - 1) == '(' || input.charAt(i - 1) == '[')) {
        // Handle negative numbers
        numberBuffer.append(c);
      } else {
        if (numberBuffer.length() > 0) {
          tokens.add(numberBuffer.toString());
          numberBuffer.setLength(0);
        }
        tokens.add(String.valueOf(c));
      }
    }
    if (numberBuffer.length() > 0) {
      tokens.add(numberBuffer.toString());
    }

    // Convert tokens to RPN
    Queue<String> rpnQueue = convertInfixToRPN(tokens.toArray(new String[0]));

    // Convert queue to a formatted string
    return String.join(" ", rpnQueue).trim();
  }

  /**
   * Converts an infix notation array to Reverse Polish Notation (RPN).
   */
  private static Queue<String> convertInfixToRPN(String[] infixNotation) {
    Map<String, Integer> precedence = Map.of(
      "/", 5, "*", 5, "+", 4, "-", 4, "(", 0, "[", 0
    );

    Queue<String> queue = new LinkedList<>();
    Deque<String> stack = new ArrayDeque<>();

    for (int i = 0; i < infixNotation.length; i++) {
      String token = infixNotation[i];

      // Handle implicit multiplication: `4[...]=4 * [...]`
      if (i > 0 && isNumber(infixNotation[i - 1]) && ("(".equals(token) || "[".equals(token))) {
        while (!stack.isEmpty() && precedence.get("*") <= precedence.getOrDefault(stack.peek(), 0)) {
          queue.add(stack.pop());
        }
        stack.push("*");
      }

      if (isNumber(token)) {
        queue.add(token);
      } else if ("(".equals(token) || "[".equals(token)) {
        stack.push(token);
      } else if (")".equals(token) || "]".equals(token)) {
        String expectedOpening = token.equals(")") ? "(" : "[";
        while (!stack.isEmpty() && !stack.peek().equals(expectedOpening)) {
          queue.add(stack.pop());
        }
        if (stack.isEmpty()) {
          throw new IllegalArgumentException("Mismatched brackets: " + token);
        }
        stack.pop();
      } else if (precedence.containsKey(token)) {
        while (!stack.isEmpty() && precedence.get(token) <= precedence.getOrDefault(stack.peek(), 0)) {
          queue.add(stack.pop());
        }
        stack.push(token);
      } else {
        throw new IllegalArgumentException("Invalid token: " + token);
      }
    }

    while (!stack.isEmpty()) {
      if ("(".equals(stack.peek()) || "[".equals(stack.peek())) {
        throw new IllegalArgumentException("Mismatched brackets");
      }
      queue.add(stack.pop());
    }

    return queue;
  }

  /**
   * Checks if a string is a number.
   */
  private static boolean isNumber(String input) {
    return input.matches("-?\\d+(\\.\\d+)?");
  }

  /**
   * Checks if a character is an operator.
   */
  private static boolean isOperator(char c) {
    return "+-*/".indexOf(c) != -1;
  }

  //  public static void main(String[] args) {
  //    String input = "8 - [ 5 - 4 [ -6 + 7 ( 5 - 2 ) ] - 3 ]";
  //    String rpnOutput = convertInfixToRPN(input);
  //    System.out.println("RPN Output: " + rpnOutput);
  //    System.out.println("Evaluation Result: " + applyRPN(rpnOutput));
  //  }
}
