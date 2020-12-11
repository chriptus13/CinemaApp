package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Collection;
import java.util.Map;

/**
 * Command to get all the Movies
 */
public class GetMoviesCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        Collection<Movie> l = repo.getMovies();
        return new MultipleCommandResult<>(l, new Movie(null, 0, 0));
    }

    @Override
    public String getDescription() {
        return "GET /movies\n" +
                "\tGet all movies";
    }
}
