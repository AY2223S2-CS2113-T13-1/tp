package seedu.duke;

import seedu.duke.exceptions.NoAccountException;

import java.util.HashMap;

public class AccountList {
    // Currency implementation only specifies one account per currency, if required would have to change to a
    // Hashmap of ArrayList of account in the future and randomly generate an ID for that account
    private static AccountList instance;
    private final HashMap<Currency, Account> accountHashMap;


    private AccountList() {
        accountHashMap = new HashMap<>();
    }

    public static AccountList getInstance() {
        if (instance == null) {
            instance = new AccountList();
        }
        return instance;
    }

    public void addAccount(Currency currency, int initialBalance) {
        if (accountHashMap.containsKey(currency)) {
            // todo throw some exception signalling account already exists
        }
        accountHashMap.put(currency, new Account(initialBalance, currency));
    }

    public void deleteAccount(Currency currency) {
        if (!accountHashMap.containsKey(currency)) {
            // todo throw some exception signalling account already exists
        }
        accountHashMap.remove(currency);
    }

    public float getBalance(Currency currency) throws NoAccountException {
        if (!accountHashMap.containsKey(currency)) {
            // todo throw some exception signalling no such account
            throw new NoAccountException();
        }
        return accountHashMap.get(currency).getBalance();
    }
}
