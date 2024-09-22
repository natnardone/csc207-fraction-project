package edu.grinnell.csc207.main;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A class for performing interactive calculations on BigFractions.
 * @author Natalie Nardone
 */
public class InteractiveCalculator {
  /**
   * Creates and runs an interactive calculator for the user.
   * @param args
   *    The command line arguments
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);

    pen.print("Enter input for calculator: ");
    pen.flush();
    String temp = input.nextLine();
    BFCalculator calc = new BFCalculator();
    BFRegisterSet reg = new BFRegisterSet();
    boolean status = true;

    while (!temp.equals("QUIT")) {
      status = true;
      String[] values = temp.split(" ");

      status = InteractiveCalculator.calcExpression(calc, values, reg);
      if (!status) {
        InteractiveCalculator.printErr();
      } // if

      temp = input.nextLine();
    } // while

    calc.clear();
    reg.clear();
    input.close();

  } // main(String[])

  /**
   * Calculates a mathematical expression.
   *
   * @param calc
   *    The calculator to use
   * @param values
   *    The values from the user
   * @param reg
   *    The register to use
   * @return true if the expression is valid, false if the expression is invalid
   */
  public static boolean calcExpression(BFCalculator calc, String[] values, BFRegisterSet reg) {
    String first = values[0];

    if (first.equals("STORE")) {
      if (InteractiveCalculator.isReg(values[1])) {
        reg.store(values[1].charAt(0), calc.get());
        return true;
      } else {
        return false;
      } // if
    } // if

    calc.clear();

    if (values.length < 3) {
      return false;
    } // if

    for (int i = 0; i < values.length; i++) {
      if (!InteractiveCalculator.checkValid(values[i])) {
        return false;
      } // if
    } // if

    if (InteractiveCalculator.isOp(first)) {
      return false;
    } // if

    // get first value
    if (InteractiveCalculator.isNum(first)) {
      calc.add(new BigFraction(first));
    } else if (InteractiveCalculator.isReg(first)) {
      calc.add(reg.get(first.charAt(0)));
    } // if

    String currentOp;
    String currentNum;
    BigFraction val;

    if (values.length % 2 != 1) {
      return false;
    } // if

    for (int o = 1, n = 2; o < values.length - 1 && n < values.length; o += 2, n += 2) {
      currentOp = values[o];
      currentNum = values[n];
      val = null;
      if (!InteractiveCalculator.isOp(currentOp)) {
        return false;
      } // if
      if (InteractiveCalculator.isNum(currentNum)) {
        val = new BigFraction(currentNum);
      } else if (InteractiveCalculator.isReg(currentNum)) {
        if (reg.get(currentNum.charAt(0)) != null) {
          val = reg.get(currentNum.charAt(0));
        } else {
          return false;
        } // if
      } else {
        return false;
      } // if

      switch (currentOp) {
        case "+":   calc.add(val);
                    break;
        case "-":   calc.subtract(val);
                    break;
        case "*":   calc.multiply(val);
                    break;
        case "/":   calc.divide(val);
                    break;
        default:    break;
      } // switch
    } // for

    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print(calc.get().toString() + "\n");
    pen.flush();
    return true;
  } // calcExpression(BFCalculator, String[], BFRegisterSet)

  /**
   * Checks if the characters in the string are valid for mathematical operations.
   *
   * @param str
   *    The string to check
   * @return true if the characters are valid and false otherwise
   */
  public static boolean checkValid(String str) {
    if ((InteractiveCalculator.isReg(str))
        || (InteractiveCalculator.isNum(str))
        || (InteractiveCalculator.isOp(str))) {
      return true;
    } // if
    return false;
  } // checkValid(String)

  /**
   * Checks if the string is a register.
   * @param str
   *    The string to check
   * @return true if the string is a register and false otherwise
   */
  public static boolean isReg(String str) {
    if ((str.length() == 1) && ('a' <= str.charAt(0)) && (str.charAt(0) <= 'z')) {
      return true;
    } // if
    return false;
  } // isReg(String)

  /**
   * Checks if the string is a number.
   * @param str
   *    The string to check
   * @return true if the string is a number and false otherwise
   */
  public static boolean isNum(String str) {
    for (int i = 0; i < str.length(); i++) {
      char temp = str.charAt(i);
      if (temp == '-' && (i != 0)) {
        return false;
      } else if ((temp == '/') && ((i == 0) || (i == str.length() - 1))) {
        return false;
      } else if ((!Character.isDigit(temp)) && (temp != '-') && (temp != '/')) {
        return false;
      } // if
    } // for
    return true;
  } // isNum(String)

  /**
   * Checks if the string is an operator.
   * @param str
   *    The string to check
   * @return true if the string is an operator and false otherwise
   */
  public static boolean isOp(String str) {
    if ((str.equals("+")) || (str.equals("-")) || (str.equals("*")) || (str.equals("/"))) {
      return true;
    } // if
    return false;
  } // isOp(String)

  /**
   * Prints an error message to the user.
   */
  public static void printErr() {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print("*** Error - Invalid Input\n");
    pen.flush();
  } // printErr
} // class InteractiveCalculator
