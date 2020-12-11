package pt.isel.ls.model.commands.post;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

/**
 * Command to insert a new Movie
 */
public class PostMovieCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {

            if (args.size() != 1) throw new InvalidNumberOfParametersException();
            Integer id = (Integer) args.get("id");
            if (id == null)
                throw new InvalidParametersException();
            id = repo.insertMovie(new Movie(id, null, -1, -1));
            return new SingleCommandResult<>(id);
            //DEPRECATED
            /*if (args.size() != 3) throw new InvalidNumberOfParametersException();
            String title = (String) args.get("title");
            Integer releaseYear = (Integer) args.get("releaseYear");
            Integer duration = (Integer) args.get("duration");
            if (title == null || releaseYear == null || duration == null || releaseYear < 1900)
                throw new InvalidParametersException();
            int id = repo.insertMovie(new Movie(title, releaseYear, duration));
            return new SingleCommandResult<>(id);*/

        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "POST /movies title=...&releaseYear=...&duration=...\n" +
                "\tAdds a movie to database";
    }
}
