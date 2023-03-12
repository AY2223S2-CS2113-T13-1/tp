package seedu.duke.commands;

import seedu.duke.Currency;
import seedu.duke.exceptions.InvalidAddCommandException;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    private Currency currency;
    private int amount;

    public AddCommand(String input) {
        super(false, input);
    }

    private Currency getCurrency(String currencyString) {
        return Currency.valueOf(currencyString);
    }

    private void processCommand() throws InvalidAddCommandException {
        String[] words = super.input.split(" ");
        // Format: [Command, CURRENCY, AMOUNT]
        boolean isValidCommand = words.length == 3;
        if (!isValidCommand) {
            throw new InvalidAddCommandException();
        }
        this.currency = getCurrency(words[1]);
        this.amount = Integer.parseInt(words[2]);
    }

    @Override
    public void execute(Ui ui) {
        try {
            processCommand();
        } catch (InvalidAddCommandException e) {
            ui.printMessage(
                    "Please check that you have provided the input in the following format: add <Currency> <Amount>"
            );
        } catch (NumberFormatException e) {
            ui.printMessage("Please provide a numerical amount");
        } catch (IllegalArgumentException e) {
            ui.printMessage("Please provide a valid currency");
        }
    }
}