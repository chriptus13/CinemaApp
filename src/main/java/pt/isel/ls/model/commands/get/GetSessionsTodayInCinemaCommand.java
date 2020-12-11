package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Collection;
import java.util.Map;

/**
 * Command to get all the Sessions for today in a Cinema
 */
public class GetSessionsTodayInCinemaCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 1) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            if (cID == null) throw new InvalidParametersException();
            Collection<Session> l = repo.getSessionsInCinemaToday(cID);
            return new MultipleCommandResult<>(l, new Session(cID, 0, 0));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/sessions/today\n" +
                "\tGet all sessions in cinema with cid at the current day";
    }
}
