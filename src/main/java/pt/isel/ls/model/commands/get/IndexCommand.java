package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Map;

public class IndexCommand implements Command {
    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
