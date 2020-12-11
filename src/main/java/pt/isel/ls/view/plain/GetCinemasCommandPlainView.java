package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.view.CommandView;

import java.util.Collection;

public class GetCinemasCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Cinema> cinemas = (Collection<Cinema>) commandResult.getValue();
        if (cinemas.isEmpty()) return "No cinemas";
        StringBuilder result = new StringBuilder();
        for(Cinema c : cinemas)
            result.append(c.toString()).append("\n");
        return result.toString();
    }
}
