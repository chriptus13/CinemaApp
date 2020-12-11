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
 * Command to get the information from a Session
 */
public class GetSessionCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 2) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer sID = (Integer) args.get("sid");
            if (cID == null || sID == null) throw new InvalidParametersException();
            return new SingleCommandResult<>(repo.getSession(cID, sID));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/sessions/{sid}\n" +
                "\tGet session with sid in cinema with cid";
    }
}
