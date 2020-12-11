package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

import java.util.Collection;

public class GetTicketsInSessionCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Ticket> tickets = (Collection<Ticket>) commandResult.getValue();
        if(tickets.size() == 0) return "{status:'No tickets in that session'}";
        StringBuilder result = new StringBuilder("{tickets:[");
        int i = 0;
        for(Ticket t : tickets)
            result.append(i++ != 0 ? ", " : "")
                    .append(Utils.toJson(Ticket.class, t));
        return result.append("]}").toString();
    }
}
