package pt.isel.ls.model.commands;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.ExitCommandResult;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Map;

public class ExitCommand implements Command {
    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        return new ExitCommandResult();
    }

    @Override
    public String getDescription() {
        return "EXIT /\n" +
                "\tExits the app";
    }
}
