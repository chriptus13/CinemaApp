package pt.isel.ls.view.json;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.view.CommandView;

public class PostTheaterCommandJSONView extends CommandView {
    @Override
    public String getStringToPrint(CommandResult commandResult) {
        Integer id = (Integer) commandResult.getValue();
        return "{status:Theater inserted!'" + ", id:" + id + "}";
    }
}
