package com.sample.math;



import org.junit.jupiter.api.Test;

import static com.sample.math.ReversePolishNotation.applyInfixNotation;
import static com.sample.math.ReversePolishNotation.applyReversePolishNotation;
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
    assertEquals(applyReversePolishNotation("1 2 +"), 3.0);
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToCalculateMultiDigitNumbers() {
    assertEquals(applyReversePolishNotation("12 3 /"), 4.0);
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleNegativeNumbers() {
    assertEquals(applyReversePolishNotation("-12 3 /"), -4.0);
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleDecimalNumbers() {
    assertEquals(applyReversePolishNotation("-12.9 3 /"), -4.3);
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations1() {
    assertEquals(applyReversePolishNotation("1 2 + 4 * 5 + 3 -"), 14.0);
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations2() {
    assertEquals(applyReversePolishNotation("3 4 * 4 5 * + 12 3 / 20 4 / + - 2 5 * + 6 -"), 27.0);
  }


  @Test
  public void applyInfixNotationTest1() {
    assertEquals(applyInfixNotation("6/2*(1+2)"), 9.0);
  }

  @Test
  public void applyInfixNotationTest2() {
    assertEquals(applyInfixNotation("3 * 4 + 4 * 5 - ( 12 / 3 + 20 / 4 ) + 2 * 5 - 6"), 27.0);
  }
}
