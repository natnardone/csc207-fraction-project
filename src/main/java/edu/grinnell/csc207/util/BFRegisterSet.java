package edu.grinnell.csc207.util;

public class BFRegisterSet {

  BigFraction[] registers;

  public BFRegisterSet() {
    registers = new BigFraction[26];
  }
    
  public void store(char register, BigFraction val) {
    int index = (int) register - 'a';
    this.registers[index] = val;
  }

  public BigFraction get(char register) {
    int index = (int) register - 'a';
    return this.registers[index];
  }
}
