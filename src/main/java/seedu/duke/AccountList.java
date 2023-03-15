package seedu.duke;


import seedu.duke.exceptions.InsufficientAccountBalance;
import seedu.duke.exceptions.AccountNotEmptyException;
import seedu.duke.exceptions.AccountAlreadyExistsException;
import seedu.duke.exceptions.NoAccountException;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
    // Currency implementation only specifies one account per currency, if required would have to change to a
    // Hashmap of ArrayList of account in the future and randomly generate an ID for that account
    private final HashMap<Currency, Account> accountHashMap;

    public AccountList () {
        accountHashMap = new HashMap<>();
    }

    /**
     * Adds an account to the account list
     *
     * @param currency       The currency of the new account to be added
     * @param initialBalance The initial balance of the new account to be added
     * @throws AccountAlreadyExistsException If the account already exists
     */
    public void addAccount (Currency currency, float initialBalance) throws AccountAlreadyExistsException {
        if (accountHashMap.containsKey(currency)) {
            throw new AccountAlreadyExistsException();
        }
        accountHashMap.put(currency, new Account((int) initialBalance, currency));
    }

    /**
     * Deletes an account from the account list
     *
     * @param currency The currency type of the account to be deleted
     * @throws NoAccountException If the account does not exist
     */
    public void deleteAccount (Currency currency) throws NoAccountException, AccountNotEmptyException {
        if (!accountHashMap.containsKey(currency)) {
            throw new NoAccountException();
        }
        if ((int) accountHashMap.get(currency).getBalance() != 0) {
            throw new AccountNotEmptyException();
        }
        accountHashMap.remove(currency);
    }

    /**
     * Gets all the accounts from the accountList.
     *
     * @return An array list containing all the accounts.
     */
    public ArrayList<Account> getAllAccounts () {
        ArrayList<Account> accounts = new ArrayList<>();
        accountHashMap.forEach(((currency, account) -> {
            accounts.add(account);
        }));
        return accounts;
    }

    /**
     * Retrieves an account for a chosen currency
     *
     * @param currency the currency of the account to be returned
     * @return the currency account
     * @throws NoAccountException if the user does not have an account for that currency
     */
    public Account getAccount (Currency currency) throws NoAccountException {
        if (!accountHashMap.containsKey(currency)) {
            throw new NoAccountException();
        }
        return accountHashMap.get(currency);
    }

    public void addAmount(Currency currency, float amount) throws NoAccountException {
        if (!accountHashMap.containsKey(currency)) {
            throw new NoAccountException();
        }

        int currentAmount = (int) accountHashMap.get(currency).getBalance();

        int newBalance = currentAmount + (int) amount;

        accountHashMap.put(currency, new Account(newBalance, currency));
    }

    public int withdrawAmount(float amount, Currency currency) throws NoAccountException, InsufficientAccountBalance {
        if (!accountHashMap.containsKey(currency)) {
            throw new NoAccountException();
        }

        int currentAmount = (int) accountHashMap.get(currency).getBalance();
        int newBalance = currentAmount - (int) amount;
        if (newBalance < 0) {
            throw new InsufficientAccountBalance();
        }
        accountHashMap.put(currency, new Account(newBalance, currency));
        return newBalance;
    }

}
