package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

import java.util.Collection;

public class GetTheatersCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Theater> theaters = (Collection<Theater>) commandResult.getValue();
        StringBuilder result = new StringBuilder("{theaters:[");
        int i = 0;
        for (Theater t : theaters)
            result.append(i++ != 0 ? ", " : "").append(Utils.toJson(Theater.class, t));
        return result.append("]}").toString();
    }
}
