package pt.isel.ls.view.plain;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class OptionCommandPlainView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return (String) commandResult.getValue();
    }
}
