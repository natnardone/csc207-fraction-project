package edu.grinnell.csc207.main;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.*;

public class InteractiveCalculator {
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
      String[] values = temp.split(" ");

      if (values[0].equals("STORE")) {
        if (InteractiveCalculator.isReg(values[1])) {
          reg.store(values[1].charAt(0), calc.get());
        } else {
          InteractiveCalculator.printErr();
        }
      } else if (values.length < 3) {
        InteractiveCalculator.printErr();
      } else {
        // checks if all values in the input string are potentially valid
        for (int i = 0; i < values.length; i++) {
          if (InteractiveCalculator.checkValid(values[1]) == false) {
            InteractiveCalculator.printErr();
            status = false;
            break;
          }
        }
        if (status == true) {
          status = InteractiveCalculator.calcExpression(calc, values, reg);
        }
      }
      temp = input.nextLine();
    }

    calc.clear();
    input.close();

    /** handle current temp
       * call functions? for keyword/character/fraction?
       * print result if needed
       * Store
       * character (register)
       * number
       * operator
       * 
       * 
       * differentiate between register, number/fraction, operator
       * parse entire line, get first value, get next operator and following value, repeat until end of line/array
       * 
       * invalid: not register, not operator, not number
       */

    // input loop
    // stop on QUIT
    // 
    /**
     * STORE keyword means store the current "recent" value in the following register
     * QUIT keyword means quit
     * register character means set recent to the value of that register
     * fraction to start means set recent to that value
     * then proceed with operations and values
     * each operation indicates the method to call
     * the value indicates the value to use in the method call
     * print result
     */
  }

  public static boolean calcExpression(BFCalculator calc, String[] values, BFRegisterSet reg) {
    String first = values[0];

    if (InteractiveCalculator.isOp(first)) {
      InteractiveCalculator.printErr();
      return false;
    }
    
    // get first value
    if (InteractiveCalculator.isNum(first)) {
      calc.add(new BigFraction(first));
    } else if (InteractiveCalculator.isReg(first)) {
      calc.add(reg.get(first.charAt(0)));
    }

    String currentOp;
    String currentNum;
    BigFraction val;

    // we know values has at least 3 elements
    // o represents operator, n represents number
    for (int o = 1, n = 2; o < values.length-1 && n < values.length; o+=2, n+=2) {
      currentOp = values[o];
      currentNum = values[n];
      val = null;
      if (!InteractiveCalculator.isOp(currentOp)) {
        return false;
      }
      if (InteractiveCalculator.isNum(currentNum)) {
        val = new BigFraction(currentNum);
      } else if (InteractiveCalculator.isReg(currentNum)) {
        if (reg.get(currentNum.charAt(0)) != null) {
          val = reg.get(currentNum.charAt(0));
        } else {
          return false;
        }
      } else {
        return false;
      }

      // we have an operator string and a number!

      switch(currentOp) {
        case "+":   calc.add(val);
                    break;
        case "-":   calc.subtract(val);
                    break;
        case "*":   calc.multiply(val);
                    break;
        case "/":   calc.divide(val);
                    break;
        default:    break;
      }
    }
    // definitely potential for errors if there's a trailing operator/num

    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print(calc.get().toString()+ "\n");
    pen.flush();
    return true;

    /** 
     * 
     * get pairs of operator and value until end of array
     * for each pair, choose correct operator and call with calc and value
     * when all the way through loop, print result, return true
     * 
     * loop through array
     * check validity-
     * operator cannot be followed by another operator
     * register or num cannot be followed by another register or num
     * 
     */
  }

  public static boolean checkValid(String str) {
    if ((InteractiveCalculator.isReg(str)) ||
        (InteractiveCalculator.isNum(str)) ||
        (InteractiveCalculator.isOp(str))) {
      return true;
    }
    return false;
  }

  public static boolean isReg(String str) {
    if ((str.length() == 1) && ('a' <= str.charAt(0)) && (str.charAt(0) <= 'z')) {
      return true;
    }
    return false;
  }

  public static boolean isNum(String str) {
    // check if whole number or fraction (separate?)
    return true;
  }

  public static boolean isOp(String str) {
    if ((str.equals("+")) || (str.equals("-")) || (str.equals("*")) || (str.equals("/"))) {
          return true;
    }
    return false;
  }

  public static void printErr() {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.print("*** Error - Invalid Input\n");
    pen.flush();
  }
}
