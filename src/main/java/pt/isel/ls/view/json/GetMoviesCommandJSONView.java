package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

import java.util.Collection;

public class GetMoviesCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Movie> movies = (Collection<Movie>) commandResult.getValue();
        StringBuilder result = new StringBuilder("{movies:[");
        int i = 0;
        for(Movie m : movies)
            result.append(i++ != 0 ? ", " : "")
                    .append(Utils.toJson(Movie.class, m));
        return result.append("]}").toString();
    }
}
