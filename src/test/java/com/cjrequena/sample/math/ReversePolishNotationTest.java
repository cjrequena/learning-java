package com.cjrequena.sample.math;



import org.junit.jupiter.api.Test;

import static com.cjrequena.sample.math.ReversePolishNotation.applyInfixNotation;
import static com.cjrequena.sample.math.ReversePolishNotation.applyReversePolishNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
In reverse Polish notation the operators follow their operands;
for instance, to add 3 and 4, one would write "3 4 +" rather than "3 + 4".
If there are multiple operations, the operator is given immediately after its second operand;
so the expression written "3 - 4 + 5" in conventional notation would be written "3 4 - 5 +" in RPN: 4 is first subtracted from 3, then 5 added to it.
An advantage of RPN is that it obviates the need for parentheses that are required by infix.
While "3 - 4 * 5" can also be written "3 - (4 * 5)", that means something quite different from "(3 - 4) * 5".
In postfix, the former could be written "3 4 5 * -", which unambiguously means "3 (4 5 *) -" which reduces to "3 20 -";
the latter could be written "3 4 - 5 *" (or 5 3 4 - *, if keeping similar formatting), which unambiguously means "(3 4 -) 5 *".
Taken from http://en.wikipedia.org/wiki/Reverse_Polish_notation.
 */
public class ReversePolishNotationTest {

  @Test
  public void applyReversePolishNotationShouldBeAbleToCalculateSingleDigitNumbers() {
    assertEquals(3.0, applyReversePolishNotation("1 2 +"));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToCalculateMultiDigitNumbers() {
    assertEquals(4.0, applyReversePolishNotation("12 3 /"));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleNegativeNumbers() {
    assertEquals(-4.0, applyReversePolishNotation("-12 3 /"));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleDecimalNumbers() {
    assertEquals(-4.3, applyReversePolishNotation("-12.9 3 /"));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations1() {
    assertEquals(14.0, applyReversePolishNotation("1 2 + 4 * 5 + 3 -"));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations2() {
    assertEquals(27.0, applyReversePolishNotation("3 4 * 4 5 * + 12 3 / 20 4 / + - 2 5 * + 6 -"));
  }


  @Test
  public void applyInfixNotationTest1() {
    assertEquals(9.0, applyInfixNotation("6/2*(1+2)"));
  }

  @Test
  public void applyInfixNotationTest2() {
    assertEquals(27.0, applyInfixNotation("3 * 4 + 4 * 5 - ( 12 / 3 + 20 / 4 ) + 2 * 5 - 6"));
  }
}
