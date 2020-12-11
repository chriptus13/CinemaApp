package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.view.CommandView;

public class GetCinemaCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Cinema c = (Cinema) commandResult.getValue();
        return c.toString();
    }
}
