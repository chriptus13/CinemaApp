package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.view.CommandView;

import java.util.Collection;

public class GetSessionsDateInCinemaCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Session> sessions = (Collection<Session>) commandResult.getValue();
        if(sessions.size() == 0) return "No session at date given in specified cinema";
        StringBuilder result = new StringBuilder();
        for(Session s : sessions)
            result.append(s.toString()).append("\n");
        return result.toString();
    }
}
