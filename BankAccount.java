/////////////////////////////////////////// FILE  HEADER /////////////////////////////////////////////
//
// Title: Exceptional Bank Teller
// Files: BankAccount.java, BankAccountTester.java, BankTeller.java, BankTellerTester.java
// This File: BankAccount.java
// 
// Name: Benjamin Tarmann
// Email: btarmann@wisc.edu
//
///////////////////////////////////////// 100 COLUMNS WIDE /////////////////////////////////////////

import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * This class models a very simple account at a bank.
 * 
 * @author Benjamin Tarmann
 */
public class BankAccount {
  private String accountID;
  private int balance;
  private ArrayList<String> transactions;

  /**
   * Creates a new bank account with a given account ID and an initial balance. A deposit
   * transaction with the initial balance is added to the account's list of transactions.
   * 
   * @param accountID      account's unique ID
   * @param initialBalance account's initial balance
   * @throws IllegalArgumentException with error message if the initial balance is less than 10
   */
  public BankAccount(String accountID, int initialBalance) {
    this.accountID = accountID;

    // checks that the initial balance is greater than or equal to 10
    if (initialBalance < 10) {
      throw new IllegalArgumentException("Initial balance must be at least $10.");
    }

    balance = initialBalance;

    // adds a deposit transaction with the initial balance
    transactions = new ArrayList<String>();
    transactions.add("1 " + Integer.toString(initialBalance));
  }

  /**
   * Gets the account ID
   * 
   * @return the account ID
   */
  public String getID() {
    return accountID;
  }

  /**
   * Gets the account balance
   * 
   * @return the account balance
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Checks if an other bank account is equal to this one
   * 
   * @param other bank account being compared to this one
   * @return true if the other account is equal to this one, false if not
   */
  public boolean equals(BankAccount other) {
    if (this.accountID == other.accountID) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Deposits an amount to this bank account. A deposit transaction with the deposit amount is added
   * to the account's list of transactions.
   * 
   * @param depositAmount amount deposited into the account
   * @throws IllegalArgumentException with error message if depositAmount is negative
   */
  public void deposit(int depositAmount) {
    // checks that the deposit amount is positive
    if (depositAmount < 0) {
      throw new IllegalArgumentException("Deposit amount must be positive.");
    }

    // changes the balance and adds a transaction to the list of transactions
    balance = balance + depositAmount;
    transactions.add("1 " + Integer.toString(depositAmount));
  }

  /**
   * This method withdraws a specific amount of money. A withdrawal transaction with the withdrawal
   * amount is added to the account's list of transactions.
   * 
   * @param withdrawAmount
   * @throws DataFormatException   if withdrawAmount is negative or not a multiple of 10
   * @throws IllegalStateException if withdrawAmount is greater than the account balance
   */
  public void withdraw(int withdrawAmount) throws DataFormatException {
    // checks that the withdraw amount is positive and a multiple of 10
    if (withdrawAmount < 0 || withdrawAmount % 10 != 0) {
      throw new DataFormatException("Withdraw amount must be positive and a multiple of 10.");
    }

    // checks that the withdraw amount is not larger than the account's balance
    if (withdrawAmount > balance) {
      throw new IllegalStateException("Withdraw amount must be less than account balance.");
    }

    balance = balance - withdrawAmount;
    transactions.add("0 " + Integer.toString(withdrawAmount));
  }

  /**
   * Gets the most recent FIVE transactions in an array of length 5. This array may contain null
   * references if the total number of transactions is less than 5. If getTransactionsCount() is
   * less than 5, these transactions should be stored at the range of indices 0 ..
   * getTransactionsCount()-1. For instance, if the total number of transactions is 0, this array
   * will contain five null references. If the total number of transactions is 1, it will contain
   * the transaction at index 0, followed by 4 null references, and so on.
   * 
   * @return the most recent transactions in an array that may contain up to 5 string references
   */
  public String[] getMostRecentTransactions() {
    String[] recentTransactions = new String[5];
    int numTransactions = getTransactionsCount();

    for (int i = 0; i < recentTransactions.length; i++) {
      if ((i + 1) > numTransactions) {
        recentTransactions[i] = null;
      } else {
        recentTransactions[i] = transactions.get(transactions.size() - i - 1);
      }
    }

    return recentTransactions;
  }

  /**
   * Gets the total number of transactions performed on this bank account, meaning the size of the
   * ArrayList of transactions of this bank account
   * 
   * @return the total number of transactions performed on this account
   */
  public int getTransactionsCount() {
    return transactions.size();
  }
}


