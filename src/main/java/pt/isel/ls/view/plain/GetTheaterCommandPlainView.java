package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.view.CommandView;

public class GetTheaterCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Theater t = (Theater) commandResult.getValue();
        return t.toString();
    }
}
