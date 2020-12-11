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
 * Command to get the information from a Theater
 */
public class GetTheaterCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 2) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            if (cID == null || tID == null) throw new InvalidParametersException();
            return new SingleCommandResult<>(repo.getTheater(cID, tID));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/theaters/{tid}\n" +
                "\tGet theater with tid in cinema with cid";
    }
}
