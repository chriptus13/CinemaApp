package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.view.CommandView;

import java.util.Collection;

public class GetTheatersCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Collection<Theater> theaters = (Collection<Theater>) commandResult.getValue();
        if (theaters.size() == 0) return "No theaters in specified cinema";
        StringBuilder result = new StringBuilder();
        for(Theater t : theaters) {
            result.append(t.toString()).append("\n");
        }
        return result.toString();
    }
}
