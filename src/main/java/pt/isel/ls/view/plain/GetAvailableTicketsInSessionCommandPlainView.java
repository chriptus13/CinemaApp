package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class GetAvailableTicketsInSessionCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return commandResult.getValue().toString() + " available seats for that session";
    }
}
