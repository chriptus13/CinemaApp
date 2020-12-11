package pt.isel.ls.model.commands.post;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

/**
 * Command to insert a new Cinema
 */
public class PostCinemaCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 2) throw new InvalidNumberOfParametersException();
            String name = (String) args.get("name");
            String city = (String) args.get("city");
            if (name == null || city == null) throw new InvalidParametersException();
            int id = repo.insertCinema(new Cinema(name, city));
            return new SingleCommandResult<>(id);
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "POST /cinemas name=...&city=...\n" +
                "\tAdds a cinema to database";
    }
}
