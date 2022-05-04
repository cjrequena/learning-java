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
 * Taken from http://en.wikipedia.org/wiki/Reverse_Polish_notation.
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
   * This receives an input maths equation that must be parsed to be applied the reverse polish notation.
   * Pushes operands into a Stack and applies a lambda function over operands if the element of the equation
   * is an operator.
   * The input is an string of operand and operators separated by whitespace.
   *
   * @param input
   * @return
   */
  public static Double applyReversePolishNotation(String input) {
    // TODO validate the input format.
    Stack<Double> operands = new Stack<>();
    Arrays.asList(input.split(" ")).stream().forEach(element -> {
      switch (element) {
        case "+":
          applyOperationToOperands(operands, (n1, n2) -> n2 + n1);
          break;
        case "-":
          applyOperationToOperands(operands, (n1, n2) -> n2 - n1);
          break;
        case "*":
          applyOperationToOperands(operands, (n1, n2) -> n2 * n1);
          break;
        case "/":
          applyOperationToOperands(operands, (n1, n2) -> n2 / n1);
          break;
        default:
          operands.push(Double.parseDouble(element));
      }
    });
    return operands.pop();
  }

  /**
   * This receive a stack of operands elements and a lambda function with the operation to be applied.
   *
   *
   * @param operands
   * @param operation
   */
  protected static void applyOperationToOperands(Stack<Double> operands, BiFunction<Double, Double, Double> operation) {
    operands.push(operation.apply(operands.pop(), operands.pop()));
  }

  public static Queue<String> convertInfixNotationToReversePolishNotation(String[] infixNotation) {

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

  public static String convertInfixNotationToReversePolishNotation(String input) {
    String regexp = "((?<=[<=|>=|==|\\+|\\*|\\-|<|>|/|=(|)])|(?=[<=|>=|==|\\+|\\*|\\-|<|>|/|=(|)]))";
    String[] infixNotation = input.replaceAll("\\s+", "").split(regexp);
    String rpn = convertInfixNotationToReversePolishNotation(infixNotation).toString();
    rpn = rpn.replaceAll("\\s+", "");
    rpn = rpn.replaceAll("[\\[\\],]", " ");
    rpn = rpn.trim();
    return rpn;
  }

  protected static boolean isNumber(String input) {
    try {
      Double.valueOf(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
