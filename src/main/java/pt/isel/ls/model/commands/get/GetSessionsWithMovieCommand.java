package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

public class GetSessionsWithMovieCommand implements Command {
    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 1) throw new InvalidNumberOfParametersException();
            Integer mID = (Integer) args.get("mid");
            if (mID == null) throw new InvalidParametersException();
            return new MultipleCommandResult<>(repo.getSessionsWithMovie(mID), new Session(0, 0, mID, null));
        } catch (ClassCastException | NumberFormatException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /movies/{mid}/sessions\n" +
                "\tGet sessions with a certain Movie";
    }
}

