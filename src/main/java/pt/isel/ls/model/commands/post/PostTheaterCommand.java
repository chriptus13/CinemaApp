package pt.isel.ls.model.commands.post;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

/**
 * Command to insert a new Theater
 */
public class PostTheaterCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 4) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            String name = (String) args.get("name");
            Integer rows = (Integer) args.get("rows");
            Integer seats = (Integer) args.get("seats");
            if (cID == null || name == null || rows == null || seats == null)
                throw new InvalidParametersException();
            int id = repo.insertTheater(new Theater(cID, name, rows, seats));
            return new SingleCommandResult<>(id);
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "POST /cinemas/{cid}/theaters name=...&rows=...&seats=...\n" +
                "\tAdds a theater to a cinema in database";
    }
}
