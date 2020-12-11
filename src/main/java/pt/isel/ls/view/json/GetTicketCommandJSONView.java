package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

public class GetTicketCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return Utils.toJson(Ticket.class, (Ticket) commandResult.getValue());
    }
}
