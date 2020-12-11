package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.view.CommandView;

public class GetTicketCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Ticket t = (Ticket) commandResult.getValue();
        return t.toString() + "\n" + t.getSession().toString();
    }
}
