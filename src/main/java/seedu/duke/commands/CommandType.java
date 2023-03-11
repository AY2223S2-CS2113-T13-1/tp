package seedu.duke.commands;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    EXIT("exit"),
    SHOW_RATE("show-rate");
    private String command;

    CommandType(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
    public static CommandType get(String command) {
        if (command == null)
            throw new NullPointerException("Command is null");
        for(CommandType commandType : CommandType.values()){
            if(commandType.getCommand().equals(command)){
                return commandType;
            }
        }
        throw new IllegalArgumentException("No command found named "+command);
    }
}