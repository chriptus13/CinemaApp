package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.Utils;

import java.util.Collection;

public class GetCinemasCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Cinema> cinemas = (Collection<Cinema>) commandResult.getValue();
        StringBuilder result = new StringBuilder("{cinemas:[");
        int i = 0;
        for(Cinema c : cinemas)
            result.append(i++ != 0 ? ", " : "")
                    .append(Utils.toJson(Cinema.class, c));

        return result.append("]}").toString();
    }
}
