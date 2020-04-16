/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Exceptional Bank Teller
// Files: BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// This File: BankTeller.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class models the BankTeller data type
 * 
 * @author Benjamin Tarmann
 */
public class BankTeller {
  private ArrayList<BankAccount> accounts;

  /**
   * Creates a new BankTeller object with an empty list of accounts
   */
  public BankTeller() {
    accounts = new ArrayList<BankAccount>();
  }

  /**
   * Adds newAccount to the list of this BankTeller
   * 
   * @param newAccount a new account to add
   * @throws IllegalArgumentException if newAccount is null
   * @throws IllegalStateException    if the id of newAccount is equal to an existing id
   */
  public void addBankAccount(BankAccount newAccount) {
    // checks that newAccount is not null
    if (newAccount == null) {
      throw new IllegalArgumentException("New account is null.");
    }

    // checks that newAccount does not have the same ID as another existing account
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getID().equals(newAccount.getID())) {
        throw new IllegalStateException(
            "New account cannot have the same ID as an existing account.");
      }
    }

    accounts.add(newAccount); // only adds new account if newAccount is not null and the ID
                              // does not already exist
  }

  /**
   * Returns the bank account that has exactly the provided identifier. Case sensitive comparison
   * must be considered.
   * 
   * @param id a string that represents an identifier of a bank account
   * @return foundAccount account that matches the id parameter
   * @throws NoSuchElementException if the account is not found with the given id parameter
   */
  public BankAccount findAccount(String id) throws NoSuchElementException {
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getID().equals(id)) {
        BankAccount foundAccount = accounts.get(i);
        return foundAccount;
      }
    }

    // throws exception if the loop is exited, which means no account was found with the id
    throw new NoSuchElementException("No account could be found with the given ID.");
  }

  /**
   * Adds a new transaction to the account's list of transactions. When added, a withdrawal or
   * deposit transaction should change the account's balance.
   * 
   * @param transaction to add
   * @param account     bank account
   * @throws DataFormatException  if the format of the transaction is not correct
   * @throws NullPointerException if account is null
   */
  public void addTransaction(String transaction, BankAccount account) throws DataFormatException {
    String[] splitTransaction = transaction.trim().split(" ");
    int transactionAmount;

    // checks that account is not null
    if (account == null) {
      throw new NullPointerException("Account is null.");
    }

    // checks that the amount the transaction is for consists only of numbers
    for (int i = 0; i < splitTransaction[splitTransaction.length - 1].length(); i++) {
      if (Character.isDigit(splitTransaction[splitTransaction.length - 1].charAt(i)) == false) {
        throw new DataFormatException("Transaction amount should consist of numbers only.");
      }
    }
    transactionAmount = Integer.valueOf(splitTransaction[splitTransaction.length - 1]);

    // checks whether the transaction is a withdrawal or deposit, or else throws an exception
    if (transaction.trim().charAt(0) == '1') {
      account.deposit(transactionAmount);
    } else if (transaction.trim().charAt(0) == '0') {
      account.withdraw(transactionAmount);
    } else {
      throw new DataFormatException(
          "Transaction does not correctly indicate deposit or withdrawal.");
    }
  }

  /**
   * Loads a set of transactions from a provided file object. Each transaction is in a separate
   * line. Each transaction line should contain two items: the transaction code "0" or "1" followed
   * by the transaction amount, separated by spaces. Extra spaces at the beginning and at the end of
   * a transaction line should be ignored. Not correctly formatted lines must be skipped. Valid
   * transactions must change the balance of the bank account. If java.util.Scanner object is used
   * to read from the file object, make sure to close the scanner object whenever a
   * FileNotFoundException was thrown or not.
   * 
   * @param file    a java.io.File object referring to a file that contains a set of transactions,
   *                each in one line
   * 
   * @param account a reference to a BankAccount object
   * @throws FileNotFoundException if the file object does not correspond to an actual file within
   *                               the file system
   * @throws NullPointerException  if the account is null
   */
  public void loadTransactions(File file, BankAccount account) throws FileNotFoundException {
    // checks that account is not null
    if (account == null) {
      throw new NullPointerException("Account is null.");
    }

    // checks that the file exists
    if (!file.exists()) {
      throw new FileNotFoundException(
          "File does not correspond to an actual file within the file system.");
    }

    Scanner fileContents = new Scanner(file);
    String fileLine;
    while (fileContents.hasNextLine()) {
      try {
        fileLine = fileContents.nextLine();
        addTransaction(fileLine, account);
      } catch (DataFormatException e) {
        continue; // if a DataFormatException is thrown, the line is not correctly formatted and is
                  // skipped
      }
    }
    fileContents.close();
  }

  /**
   * Returns the total number of accounts created so far (i.e., the size of the ArrayList of
   * accounts)
   * 
   * @return the total number of accounts added to this BankTeller
   */
  public int getAccountsCount() {
    return accounts.size();
  }
}

