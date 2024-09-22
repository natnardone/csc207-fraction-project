package edu.grinnell.csc207.util;

/**
 * A class to perform calculations on fractions.
 * @author Natalie Nardone
 */
public class BFCalculator {

  /**
   * A field for the most recent calculation.
   */
  BigFraction recent;

  /**
   * Creates a new calculator with a starting value of 0/1.
   */
  public BFCalculator() {
    recent = new BigFraction(0, 1);
  } // BFCalculator

  /**
   * Gets the most recent calculated value.
   * @return the value of the recent field
   */
  public BigFraction get() {
    if (this.recent != null) {
      return this.recent;
    } // if
    this.recent = new BigFraction(0, 1);
    return this.recent;
  } // get

  /**
   * Adds the given value to the most recent value.
   * @param val
   */
  public void add(BigFraction val) {
    this.recent = this.get().add(val);
  } // add(BigFraction)

  /**
   * Subtracts the given value from the most recent value.
   * @param val
   */
  public void subtract(BigFraction val) {
    this.recent = this.get().subtract(val);
  } // subtract(BigFraction)

  /**
   * Multiplies the given value by the most recent value.
   * @param val
   */
  public void multiply(BigFraction val) {
    this.recent = this.get().multiply(val);
  } // multiply(BigFraction)

  /**
   * Divides the most recent value by the given value.
   * @param val
   */
  public void divide(BigFraction val) {
    this.recent = this.get().divide(val);
  } // divide(BigFraction)

  /**
   * Clears the calculator.
   */
  public void clear() {
    this.recent = new BigFraction(0, 1);
  } // clear

} // class BFCalculator
