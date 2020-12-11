package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

public class GetCinemaCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return Utils.toJson(Cinema.class, (Cinema) commandResult.getValue());
    }
}
