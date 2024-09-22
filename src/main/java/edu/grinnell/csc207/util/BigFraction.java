package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Samuel A. Rebelsky from BigFraction lab
 * @author Natalie Nardone
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
   * (1) Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   *
   * (2) Fractions are not necessarily stored in simplified form. To
   * obtain a fraction in simplified form, one must call the `simplify`
   * method.
   */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
    // definitely potential for errors if there's a trailing operator/num
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    String[] substrings = str.split("/");
    if (substrings.length == 1) {
      this.denom = new BigInteger("1");
    } else {
      this.denom = new BigInteger(substrings[1]);
    } // if
    this.num = new BigInteger(substrings[0]);
  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
  } // add(BigFraction)

  /**
   * Subtract a fraction from this fraction.
   *
   * @param subtr
   *    The fraction to subtract
   * @return the result of the subtraction
   */
  public BigFraction subtract(BigFraction subtr) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultNumerator = (this.num.multiply(subtr.denom)).subtract(subtr.num.multiply(this.denom));
    resultDenominator = this.denom.multiply(subtr.denom);
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
  } // subtract(BigFraction)

  /**
   * Multiply a fraction with this fraction.
   *
   * @param mult
   *    The fraction to multiply
   * @return the result of the multiplication
   */
  public BigFraction multiply(BigFraction mult) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultNumerator = this.num.multiply(mult.num);
    resultDenominator = this.denom.multiply(mult.denom);
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
  } // multiply(BigFraction)

  /**
   * Divide this fraction by a fraction.
   *
   * @param divisor
   *    The fraction to divide by
   * @return the result of the division
   */
  public BigFraction divide(BigFraction divisor) {
    BigFraction flippedDivisor;
    flippedDivisor = new BigFraction(divisor.denom, divisor.num);
    return this.multiply(flippedDivisor);
  } // divide(BigFraction)

  /**
   * Simplifies the given fraction by the GCD of the numerator and denominator.
   * @return the simplified fraction
   */
  public BigFraction simplify() {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    BigInteger gcd = this.num.gcd(this.denom);

    resultDenominator = this.denom.divide(gcd);
    resultNumerator = this.num.divide(gcd);

    return new BigFraction(resultNumerator, resultDenominator);

  } // simplify

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.simplify().denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.simplify().num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    BigFraction simplify = this.simplify();
    // Special case: It's zero
    if (simplify.num.equals(BigInteger.ZERO)) {
      return "0";
    } else if (simplify.denom.equals(new BigInteger("1"))) {
      return simplify.num + "";
    } // if

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return simplify.num + "/" + simplify.denom;
  } // toString()
} // class BigFraction
