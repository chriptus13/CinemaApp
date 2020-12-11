package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

import java.util.Collection;

public class GetSessionsCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Session> sessions = (Collection<Session>) commandResult.getValue();
        if(sessions.size() == 0) return "{status:'No session at date given in specified cinema'}";
        StringBuilder result = new StringBuilder("{sessions:[");
        int i = 0;
        for(Session s : sessions)
            result.append(i++ != 0 ? ", " : "")
                    .append(Utils.toJson(Session.class, s));
        return result.append("]}").toString();
    }
}
