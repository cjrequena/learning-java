package com.sample.math;

import org.junit.Test;

import static com.sample.math.ReversePolishNotation.applyReversePolishNotation;
import static com.sample.math.ReversePolishNotation.applyInfixNotation;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
    assertThat(applyReversePolishNotation("1 2 +"), is(equalTo(3.0)));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToCalculateMultiDigitNumbers() {
    assertThat(applyReversePolishNotation("12 3 /"), is(equalTo(4.0)));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleNegativeNumbers() {
    assertThat(applyReversePolishNotation("-12 3 /"), is(equalTo(-4.0)));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleDecimalNumbers() {
    assertThat(applyReversePolishNotation("-12.9 3 /"), is(equalTo(-4.3)));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations1() {
    assertThat(applyReversePolishNotation("1 2 + 4 * 5 + 3 -"), is(equalTo(14.0)));
  }

  @Test
  public void applyReversePolishNotationShouldBeAbleToHandleMoreComplexNotations2() {
    assertThat(applyReversePolishNotation("3 4 * 4 5 * + 12 3 / 20 4 / + - 2 5 * + 6 -"), is(equalTo(27.0)));
  }


  @Test
  public void applyInfixNotationTest1() {
    assertThat(applyInfixNotation("6/2*(1+2)"), is(equalTo(9.0)));
  }

  @Test
  public void applyInfixNotationTest2() {
    assertThat(applyInfixNotation("3 * 4 + 4 * 5 - ( 12 / 3 + 20 / 4 ) + 2 * 5 - 6"), is(equalTo(27.0)));
  }
}
