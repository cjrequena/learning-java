package com.sample.math;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.*;

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

  public static Double calc(String input) {
    Stack<Double> numbers = new Stack<>();
    Arrays.asList(input.split(" ")).stream().forEach(number -> {
      switch (number) {
        case "+":
          calcSign(numbers, (n1, n2) -> n2 + n1);
          break;
        case "-":
          calcSign(numbers, (n1, n2) -> n2 - n1);
          break;
        case "*":
          calcSign(numbers, (n1, n2) -> n2 * n1);
          break;
        case "/":
          calcSign(numbers, (n1, n2) -> n2 / n1);
          break;
        default:
          numbers.push(Double.parseDouble(number));
      }
    });
    return numbers.pop();
  }

  protected static Stack<Double> calcSign(Stack<Double> numbers, BiFunction<Double, Double, Double> operation) {
    numbers.push(operation.apply(numbers.pop(), numbers.pop()));
    return numbers;
  }
}
