package pt.isel.ls.model;

import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.CommandExecutionException;

import java.util.Map;

/**
 *  Specifies the operations for a command
 */
public interface Command {

    /**
     * Executes the command with the arguments in the map
     * @param args arguments to execute the command
     * @param repo repository where to execute the command
     * @return Returns the result of execution from the command
     * @throws CommandExecutionException if occurs any execution exception
     */
    CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException;

    /**
     * Gets a description for the command
     * @return Returns the Command Description
     */
    String getDescription();
}
