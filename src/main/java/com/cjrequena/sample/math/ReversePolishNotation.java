package com.cjrequena.sample.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * <p>
 * In Reverse Polish Notation the operators follow their operands;
 * for instance, to add 3 and 4, one would write "3 4 +" rather than "3 + 4".
 *
 * If there are multiple operations, the operator is given immediately after its second operand;
 * so the expression written "3 - 4 + 5" in conventional notation would be written "3 4 - 5 +" in RPN: 4 is first subtracted from 3, then 5 added to it.
 *
 * An advantage of RPN is that it obviates the need for parentheses that are required by infix.
 *
 * While "3 - 4 * 5" can also be written "3 - (4 * 5)", that means something quite different from "(3 - 4) * 5".
 *
 * In postfix, the former could be written "3 4 5 * -", which unambiguously means "3 (4 5 *) -" which reduces to "3 20 -";
 * the latter could be written "3 4 - 5 *" (or 5 3 4 - *, if keeping similar formatting), which unambiguously means "(3 4 -) 5 *".
 *
 * Taken from <a href="http://en.wikipedia.org/wiki/Reverse_Polish_notation">...</a>.
 * <p>
 *
 * @author cjrequena
 * @version 1.0
 * @see <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">https://en.wikipedia.org/wiki/Reverse_Polish_notation</a>
 * @see <a href="https://technologyconversations.com/2014/03/28/java-8-tutorial-through-katas-reverse-polish-notation-medium/">https://technologyconversations.com/2014/03/28/java-8-tutorial-through-katas-reverse-polish-notation-medium/</a>
 * <p>
 * @see
 * @since JDK1.8
 */
public class ReversePolishNotation {

  /**
   *
   * @param input
   * @return
   */
  public static Double applyInfixNotation(String input) {
    // TODO validate the input format.
    return applyReversePolishNotation(convertInfixNotationToReversePolishNotation(input));
  }

  /**
   * Evaluates a mathematical expression written in Reverse Polish Notation (RPN).
   * <p>
   * The input should be a space-separated string of numbers and operators.
   * Operands are pushed onto a stack, and when an operator is encountered,
   * the corresponding operation is applied to the top two operands.
   * </p>
   *
   * <p>Example usage:</p>
   * <pre>
   * applyReversePolishNotation("5 2 +")  // returns 7.0  (5 + 2)
   * applyReversePolishNotation("5 2 -")  // returns 3.0  (5 - 2)
   * applyReversePolishNotation("5 2 *")  // returns 10.0 (5 * 2)
   * applyReversePolishNotation("5 2 /")  // returns 2.5  (5 / 2)
   * </pre>
   *
   * @param input the mathematical expression in Reverse Polish Notation (RPN)
   * @return the evaluated result as a Double
   * @throws IllegalArgumentException if the input is invalid or contains insufficient operands
   */
  public static Double applyReversePolishNotation(String input) {
    // TODO validate the input format.
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException("Input cannot be null or empty.");
    }

    Stack<Double> operands = new Stack<>();

    Arrays.stream(input.split(" ")).forEach(element -> {
      switch (element) {
        case "+":
          applyOperationToOperands(operands, Double::sum);
          break;
        case "-":
          applyOperationToOperands(operands, (operand1, operand2) -> operand1 - operand2);
          break;
        case "*":
          applyOperationToOperands(operands, (operand1, operand2) -> operand1 * operand2);
          break;
        case "/":
          applyOperationToOperands(operands, (operand1, operand2) -> {
            if (operand2 == 0) throw new ArithmeticException("Division by zero is not allowed.");
            return operand1 / operand2;
          });
          break;
        default:
          try {
            operands.push(Double.parseDouble(element));
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid token in input: " + element);
          }
      }
    });
    return operands.pop();
  }

  /**
   * Applies a binary operation to the top two elements of the operand stack.
   * The result is pushed back onto the stack.
   *
   * @param operands  the stack containing numerical operands
   * @param operation a lambda function representing the binary operation to apply
   * @throws IllegalArgumentException if the stack contains fewer than two operands
   */
  private static void applyOperationToOperands(Stack<Double> operands, BiFunction<Double, Double, Double> operation) {
    if (operands.size() < 2) {
      throw new IllegalArgumentException("Insufficient operands for the operation.");
    }
    double operand2 = operands.pop(); // Pop second operand first (since it's on top)
    double operand1 = operands.pop(); // Pop first operand
    operands.push(operation.apply(operand1, operand2)); // Apply operation and push result
  }

  private static Queue<String> convertInfixNotationToReversePolishNotation(String[] infixNotation) {

    Map<String, Integer> precedence = new HashMap<>();
    precedence.put("/", 5);
    precedence.put("*", 5);
    precedence.put("+", 4);
    precedence.put("-", 4);
    precedence.put("(", 0);

    Queue<String> queue = new LinkedList<>();
    Stack<String> stack = new Stack<>();

    for (String token : infixNotation) {
      if ("(".equals(token)) {
        stack.push(token);
        continue;
      }

      if (")".equals(token)) {
        while (!"(".equals(stack.peek())) {
          queue.add(stack.pop());
        }
        stack.pop();
        continue;
      }
      // an operator
      if (precedence.containsKey(token)) {
        while (!stack.empty() && precedence.get(token) <= precedence.get(stack.peek())) {
          queue.add(stack.pop());
        }
        stack.push(token);
        continue;
      }

      if (isNumber(token)) {
        queue.add(token);
        continue;
      }

      throw new IllegalArgumentException("Invalid input");
    }
    // at the end, pop all the elements in stack to queue
    while (!stack.isEmpty()) {
      queue.add(stack.pop());
    }

    return queue;
  }

  private static String convertInfixNotationToReversePolishNotation(String input) {
    // Define regex to split operators and operands while preserving necessary characters
    String regex = "(?<=\\b|[+*/<>=()-])|(?=\\b|[+*/<>=()-])";

    // Remove all whitespace and split the input expression based on regex
    String[] infixNotation = input.replaceAll("\\s+", "").split(regex);

    // Convert to Reverse Polish Notation
    Queue<String> rpnQueue = convertInfixNotationToReversePolishNotation(infixNotation);

    // Convert queue to a formatted string
    return String.join(" ", rpnQueue).trim();
  }


  private static boolean isNumber(String input) {
    try {
      Double.valueOf(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
