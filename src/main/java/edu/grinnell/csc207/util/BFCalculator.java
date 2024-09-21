package edu.grinnell.csc207.util;

public class BFCalculator {

  BigFraction recent;

  public BFCalculator() {
    recent = new BigFraction(0, 1);
  }

  public BigFraction get() {
    if (this.recent != null) {
      return this.recent;
    }
    this.recent = new BigFraction(0, 1);
    return this.recent;
  }

  public void add(BigFraction val) {
    this.recent = this.get().add(val);
  }

  public void subtract(BigFraction val) {
    this.recent = this.get().subtract(val);
  }

  public void multiply(BigFraction val) {
    this.recent = this.get().multiply(val);
  }

  public void divide(BigFraction val) {
    this.recent = this.get().divide(val);
  }

  public void clear() {
    this.recent = new BigFraction(0, 1);
  }

}
