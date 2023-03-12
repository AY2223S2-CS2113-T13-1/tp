package seedu.duke.ui;

import seedu.duke.constants.Message;

import java.util.Scanner;

/**
 * The UI Class is used to display the messages to the user.
 */
public class Ui {
    /**
     * The getUserInput reads in the user input as a string
     * and returns the input for the parser.
     *
     * @return The user input as a string.
     */
    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        return input;
    }

    /**
     * The printMessage prints out the message.
     *
     * @param message The message in any type.
     */
    public static void printMessage(Object message) {
        System.out.println(message);
    }

    /**
     * The printf is used to print based on the format and the arguments
     * given. This function is used to mimic {@code System.out.printf}
     *
     * @param format The string format to be printed.
     * @param args   The arguments to be printed.
     * @throws java.util.IllegalFormatException If an invalid format given.
     * @throws NullPointerException             If the format is null.
     **/
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    /**
     * The printGreeting prints out the welcome message upon
     * the initialisation of the chatbot.
     */
    public static void printGreeting() {
        printMessage(Message.GREETING.getMessage());
    }

    /**
     * The printFarewell prints out the welcome message upon
     * the termination of the chatbot.
     */
    public static void printFarewell() {
        printMessage(Message.FAREWELL.getMessage());
    }

    /**
     * The printHelp prints out the help message based on the user's request.
     */
    public static void printHelp() {
        printMessage(Message.HELP.getMessage());
    }

    /**
     * The printSpacer is used to print hyphens to make the code looks neater.
     **/
    public static void printSpacer() {
        printMessage("-".repeat(50));
    }
}