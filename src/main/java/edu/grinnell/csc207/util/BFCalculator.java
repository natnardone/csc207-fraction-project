package edu.grinnell.csc207.util;

public class BFCalculator {

  private BigFraction recent;

  public BigFraction get() {
    if (this.recent != null) {
      return this.recent;
    }
    this.recent = new BigFraction(0, 0);
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
    this.recent = new BigFraction(0, 0);
  }

}
