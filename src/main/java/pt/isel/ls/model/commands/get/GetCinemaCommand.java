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
 * Command to get the information from a Cinema
 */
public class GetCinemaCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 1) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            if (cID == null) throw new InvalidParametersException();
            return new SingleCommandResult<>(repo.getCinema(cID));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}\n" +
                "\tGet cinema with cid";
    }
}
