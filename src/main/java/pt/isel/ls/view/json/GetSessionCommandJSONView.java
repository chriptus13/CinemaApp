package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

public class GetSessionCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return Utils.toJson(Session.class, (Session) commandResult.getValue());
    }
}
