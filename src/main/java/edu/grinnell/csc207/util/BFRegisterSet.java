package edu.grinnell.csc207.util;

/**
 * A class for containing BigFraction values in registers.
 * @author Natalie Nardone
 */
public class BFRegisterSet {

  /**
   * A field for a set of registers.
   */
  BigFraction[] registers;

  /**
   * Creates a new register with 26 spaces.
   */
  public BFRegisterSet() {
    registers = new BigFraction[26];
  } // BFRegisterSet

  /**
   * Stores the given value in the given register.
   *
   * @param register
   *    The register to store the value in
   * @param val
   *    The value to store in the register
   */
  public void store(char register, BigFraction val) {
    int index = (int) register - 'a';
    this.registers[index] = val;
  } // store(char, BigFraction)

  /**
   * Gets the stored value from the given register.
   *
   * @param register
   *    The register to access the value of
   * @return the value in the given register
   */
  public BigFraction get(char register) {
    int index = (int) register - 'a';
    return this.registers[index];
  } // get(char)

  /**
   * Clears the register of all values.
   */
  public void clear() {
    registers = new BigFraction[26];
  } // clear
} // class BFRegisterSet
