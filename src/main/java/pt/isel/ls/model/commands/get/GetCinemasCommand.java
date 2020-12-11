package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Collection;
import java.util.Map;

/**
 * Command to get all the Cinemas
 */
public class GetCinemasCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        if (args.size() != 0) throw new InvalidParametersException();
        Collection<Cinema> l = repo.getCinemas();
        return new MultipleCommandResult<>(l, new Cinema(null, null));
    }

    @Override
    public String getDescription() {
        return "GET /cinemas\n" +
                "\tGet all cinemas";
    }
}
