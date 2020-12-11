package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class PostMovieCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Integer id = (Integer) commandResult.getValue();
        return "Inserted movie with id " + id;
    }
}
