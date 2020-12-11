package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class GetAvailableTicketsInSessionCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return "{available_seats:" + commandResult.getValue().toString() + "}";
    }
}
