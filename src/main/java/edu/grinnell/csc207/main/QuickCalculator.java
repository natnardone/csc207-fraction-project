package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * A class for performing quick calculations on BigFractions.
 * @author Natalie Nardone
 */
public class QuickCalculator {
  /**
   * Creates and runs a quick calculator for the user.
   * @param args
   *    The command line arguments
   */
  public static void main(String[] args) {

    BFCalculator calc = new BFCalculator();
    BFRegisterSet reg = new BFRegisterSet();
    boolean status = true;

    // loop through each expression in the input
    for (int i = 0; i < args.length; i++) {
      if ((args[i].equals("")) || (args[i].equals(" ")) || (args[i].charAt(0) == ' ')) {
        InteractiveCalculator.printErr();
      } else {
        status = true;
        String[] values = args[i].split(" ");

        status = InteractiveCalculator.calcExpression(calc, values, reg);
        if (!status) {
          InteractiveCalculator.printErr();
        } // if
      } // if/else
    } // for

    calc.clear();
    reg.clear();

  } // main(String[])

} // class QuickCalculator
