package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

public class GetMovieCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return Utils.toJson(Movie.class, (Movie) commandResult.getValue());
    }
}
