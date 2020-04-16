/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Exceptional Bank Teller
// Files: BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// This File: BankTellerTester.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is a tester for the BankTeller class's public behaviors
 * 
 * @author Benjamin Tarmann
 *
 */
public class BankTellerTester {
  /**
   * Checks whether the constructor of BankTeller class creates a new BankTeller object with an
   * empty ArrayList of bank accounts.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankTellerConstructor() {
    BankTeller test = new BankTeller();
    if (test.getAccountsCount() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the BankTeller.addBankAccount() method throws an IllegalStateException when it
   * is passed a bank account with an identifier already used.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankTellerAddBankAccountUsedIdentifier() {
    try {
      BankTeller testTeller = new BankTeller();
      BankAccount testAccount1 = new BankAccount("0001", 20);
      BankAccount testAccount2 = new BankAccount("0001", 20);
      testTeller.addBankAccount(testAccount1);
      testTeller.addBankAccount(testAccount2);
      return false; // test fails if exception is not thrown from above line
    } catch (IllegalStateException e) {
      return true;
    }
  }

  /**
   * This method checks whether the BankTeller.loadTransactions() method that takes a File parameter
   * throws a FileNotFoundException, when it is passed a File object that does not correspond to an
   * actual file within the file system, and a non null reference of type BankAccount.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testBankTellerLoadTransactionsFileNotFound() {
    BankTeller testTeller = new BankTeller();
    File testFile = new File("test.txt");
    BankAccount testAccount = new BankAccount("0001", 20);

    try {
      testTeller.loadTransactions(testFile, testAccount);
      return false;
    } catch (FileNotFoundException e) {
      return true;
    }
  }

  /**
   * Calls the test methods defined in this BankTellerTester class
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (testBankTellerConstructor() == false) {
      System.out.println("testBankTellerConstructor failed.");
    }

    if (testBankTellerAddBankAccountUsedIdentifier() == false) {
      System.out.println("testBankTellerAddBankAccountUsedIdentifier failed.");
    }

    if (testBankTellerLoadTransactionsFileNotFound() == false) {
      System.out.println("testBankTellerLoadTransactionsFileNotFound failed.");
    }
  }

}
