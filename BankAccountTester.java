/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Exceptional Bank Teller
// Files: BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// This File: BankAccountTester.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * This class represents a tester for the BankAccount class's public behaviors
 * 
 * @author Benjamin Tarmann
 */
public class BankAccountTester {

  /**
   * Calls the constructor of BankAccount class to create a new BankAccount object with a given id
   * and a valid initial balance (greater of equal to 10). Checks whether the new account is created
   * with the provided account id and balance. It checks also that the list of transactions of the
   * created account contains only one transaction /"1 " + the initial balance/
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBankAccountConstructorValidInitialBalance() {
    BankAccount test = new BankAccount("0001", 20);

    // checks that the BankAccount object has the correct ID, balance, and transactions
    if (test.getID().equals("0001") && test.getBalance() == 20 && test.getTransactionsCount() == 1
        && test.getMostRecentTransactions()[0].equals("1 20")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks whether the BankAccount constructor throws an IllegalArgumentException when
   * it is passed a balance smaller than 10.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBankAccountConstructorNotValidInitialBalance() {
    try {
      BankAccount test = new BankAccount("0001", 5);
    } catch (IllegalArgumentException e) {
      return true; // test passes if exception is thrown and caught
    }
    return false; // test fails if exception is not thrown from above line
  }

  /**
   * Checks whether BankAccount.equals() method returns true when it compares a bank account to
   * another one having the same account identifier (case sensitive comparison); and it returns
   * false otherwise.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBankAccountEquals() {
    BankAccount test1 = new BankAccount("0001", 20);
    BankAccount test2 = new BankAccount("0001", 20);

    if (test1.equals(test2) != true) {
      return false;
    }
    return true; // returns true if above test passes
  }

  /**
   * Checks whether BankAccount.withdraw() method throws a DataFormatException when it is passed a
   * negative number or a number not multiple of 10. The account balance should not change after the
   * withdraw() method returns.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBankAccountWithdrawInvalidAmount() {
    BankAccount test = new BankAccount("0001", 20);

    // checks if DataFormatException is thrown with a negative withdraw amount
    boolean test1Passed = false;
    try {
      test.withdraw(-10);
    } catch (DataFormatException e) {
      test1Passed = true;
    }

    // checks if DataFormatException is thrown with a withdraw amount not a multiple of 10
    boolean test2Passed = false;
    try {
      test.withdraw(1);
    } catch (DataFormatException e) {
      test2Passed = true;
    }

    if (test1Passed == true && test2Passed == true) {
      return true; // test passes if a DataFormatException is thrown and caught in both tests above
    } else {
      return false;
    }
  }

  /**
   * Checks whether BankAccount.withdraw() method throws an IllegalStateException when it is passed
   * a number larger than the account's balance. The account balance should not change after that
   * withdraw() method call returns.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankAccountWithdrawLargerOfBalanceAmount() {
    BankAccount test = new BankAccount("0001", 20);

    try {
      test.withdraw(50);
    } catch (IllegalStateException e) {
      return true; // test passes since IllegalStateException is thrown and caught
    } catch (DataFormatException e) {
      return false; // test fails since a DataFormatException should not be thrown
    }
    return false; // test fails if no exception is thrown at all
  }

  /**
   * Checks whether BankAccount.withdraw() method returns without any exception when it is passed a
   * positive number smaller than the account's balance. The withdrawal amount should be subtracted
   * from the balance after the withdraw() method call returns.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankAccountWithdrawValidAmount() {
    BankAccount test = new BankAccount("0001", 20);

    try {
      test.withdraw(10);
    } catch (IllegalStateException e) {
      return false; // test fails since an IllegalStateException should not be thrown
    } catch (DataFormatException e) {
      return false; // test fails since a DataFormatException should not be thrown
    }

    // checks that the new balance after the withdraw is 10
    if (test.getBalance() != 10) {
      return false;
    }

    // checks that a withdraw transaction was added to the transactions array
    if (test.getTransactionsCount() != 2 || !test.getMostRecentTransactions()[0].equals("0 10")) {
      return false;
    }

    return true;
  }

  /**
   * Checks whether BankAccount.deposit() method throws an IllegalArgumentException when it is
   * passed a negative number. The balance of the bank account should not change after the method
   * call returns.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankAccountDepositNegativeAmount() {
    BankAccount test = new BankAccount("0001", 20);

    try {
      test.deposit(-10);
    } catch (IllegalArgumentException e) {
      return true; // test passes if exception is thrown and caught
    }

    return false; // test fails since an IllegalArgumentException is not thrown with a negative
                  // deposit amount
  }

  /**
   * Main method calls all of the test methods and prints out a message if one or more fail.
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (testBankAccountConstructorValidInitialBalance() == false) {
      System.out.println("testBankAccountConstructorValidInitialBalance failed");
    }

    if (testBankAccountConstructorNotValidInitialBalance() == false) {
      System.out.println("testBankAccountConstructorNotValidInitialBalance failed");
    }

    if (testBankAccountEquals() == false) {
      System.out.println("testBankAccountEquals failed");
    }

    if (testBankAccountWithdrawInvalidAmount() == false) {
      System.out.println("testBankAccountWithdrawInvalidAmount failed");
    }

    if (testBankAccountWithdrawLargerOfBalanceAmount() == false) {
      System.out.println("testBankAccountWithdrawLargerOfBalanceAmount failed");
    }

    if (testBankAccountWithdrawValidAmount() == false) {
      System.out.println("testBankAccountWithdrawValidAmount failed");
    }

    if (testBankAccountDepositNegativeAmount() == false) {
      System.out.println("testBankAccountDepositNegativeAmount failed");
    }
  }

}
