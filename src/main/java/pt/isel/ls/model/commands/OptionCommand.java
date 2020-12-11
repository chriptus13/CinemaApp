package pt.isel.ls.model.commands;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Map;

public class OptionCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        StringBuilder res = new StringBuilder();
        for (Object o : args.values())
            res.append((String) o).append('\n');
        return new SingleCommandResult<>(res.toString());
    }

    @Override
    public String getDescription() {
        return "OPTION /\n" +
                "\tShows the commands that can be executed";
    }
}
