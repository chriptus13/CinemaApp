package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.view.CommandView;

public class GetSessionCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Session m = (Session) commandResult.getValue();
        return m.toString();
    }
}
