package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.view.CommandView;

import java.util.Collection;

public class GetMoviesCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Movie> movies = (Collection<Movie>) commandResult.getValue();
        if (movies.isEmpty()) return "No movies";
        StringBuilder result = new StringBuilder();
        for(Movie m : movies)
            result.append(m.toString()).append("\n");
        return result.toString();
    }
}
