package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

/**
 * Command to get the information from a Movie
 */
public class GetMovieCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 1) throw new InvalidNumberOfParametersException();
            Integer mID = (Integer) args.get("mid");
            if (mID == null) throw new InvalidParametersException();
            return new SingleCommandResult<>(repo.getMovie(mID));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /movies/{mid}\n" +
                "\tGet movie with mid";
    }
}
