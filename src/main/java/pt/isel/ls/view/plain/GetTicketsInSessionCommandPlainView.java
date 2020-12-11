package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.view.CommandView;

import java.util.Collection;

public class GetTicketsInSessionCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Ticket> tickets = (Collection<Ticket>) commandResult.getValue();
        if(tickets.size() == 0) return "No tickets in that session";
        StringBuilder result = new StringBuilder();
        for(Ticket t : tickets) {
            result.append(t.toString()).append("\n");
        }
        return result.toString();
    }
}
