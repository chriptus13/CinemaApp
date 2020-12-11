package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.view.CommandView;

public class GetMovieCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Movie m = (Movie) commandResult.getValue();
        return m.toString();
    }
}
