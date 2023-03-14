package seedu.duke.commands;

import seedu.duke.AccountList;
import seedu.duke.Currency;
import seedu.duke.constants.ErrorMessage;
import seedu.duke.constants.Message;
import seedu.duke.exceptions.InsufficientAccountBalance;
import seedu.duke.exceptions.InvalidAddCommandException;
import seedu.duke.exceptions.NegativeWithdrawalAmountException;
import seedu.duke.exceptions.NoAccountException;
import seedu.duke.ui.Ui;

/**
 * This class is used to deal with the withdrawCommand.
 */
public class WithdrawCommand extends Command {
    private Currency currency;
    private int amount;

    /**
     * @param input   The user input including the command.
     */
    public WithdrawCommand(String input) {
        super(false, input);
    }

    private Currency getCurrency(String currencyString) {
        return Currency.valueOf(currencyString);
    }

    private void processCommand() throws InvalidAddCommandException, NegativeWithdrawalAmountException {
        String[] words = super.input.split(" ");
        // Format: [Command, CURRENCY, AMOUNT]
        boolean isValidCommand = words.length == 3;
        if (!isValidCommand) {
            throw new InvalidAddCommandException();
        }
        this.currency = getCurrency(words[2]);
        this.amount = Integer.parseInt(words[1]) * 100;
        if(this.amount <0 ){
            throw new NegativeWithdrawalAmountException();
        }


    }

    private void printSuccess(Ui ui, float newBalance) {
        ui.printf(Message.SUCCESSFUL_WITHDRAW_COMMAND.getMessage(), newBalance, this.currency.name(),
                (float)this.amount/100, this.currency.name());
    }

    /**
     * Withdraw the currency into the existing account if found and print a success message.
     *
     * @param ui The instance of the UI class.
     */
    @Override
    public void execute(Ui ui, AccountList accounts) {
        try {
            processCommand();
            float newBalance = accounts.withdrawAmount(this.amount, this.currency)/100;
            printSuccess(ui, newBalance);
        } catch (InvalidAddCommandException e) {
            ui.printMessage(ErrorMessage.INVALID_ADD_COMMAND);
        } catch (NumberFormatException e) {
            ui.printMessage(ErrorMessage.INVALID_NUMERICAL_AMOUNT);
        } catch (IllegalArgumentException e) {
            ui.printMessage(ErrorMessage.INVALID_CURRENCY);
        } catch (NoAccountException e) {
            ui.printMessage(ErrorMessage.NO_SUCH_ACCOUNT);
        } catch (InsufficientAccountBalance e) {
            ui.printMessage(ErrorMessage.INSUFFICIENT_WITHDRAW_BALANCE);
        }catch (NegativeWithdrawalAmountException e){
            ui.printMessage(ErrorMessage.NEGATIVE_WITHDRAWAL_AMOUNT);
        }
    }
}