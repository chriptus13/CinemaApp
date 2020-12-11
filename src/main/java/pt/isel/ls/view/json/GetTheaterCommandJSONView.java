package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

public class GetTheaterCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return Utils.toJson(Theater.class, (Theater) commandResult.getValue());
    }
}
