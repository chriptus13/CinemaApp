package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class DeleteTicketsCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return "{status:'Tickets deleted'}";
    }
}
