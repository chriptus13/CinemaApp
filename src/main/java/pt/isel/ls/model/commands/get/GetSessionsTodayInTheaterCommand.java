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
 * Command to get all the Sessions for today in a Theater
 */
public class GetSessionsTodayInTheaterCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 2) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            if (cID == null || tID == null) throw new InvalidParametersException();
            Collection<Session> l = repo.getSessionsInTheaterToday(cID, tID);
            return new MultipleCommandResult<>(l, new Session(cID, tID, 0));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/theaters/{tid}/sessions/today\n" +
                "\tGet all sessions in theater with tid at the current day";
    }
}
