package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;

public class DeleteTicketsCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Delete Tickets";
    private static final String DEFAULT_CONTENT = "<h1>Tickets Removed</h1>\n";

    @Override
    public String getTitle(CommandResult commandResult) {
        return TITLE;
    }

    @Override
    public String getCSSStyles() {
        return "";
    }

    @Override
    public String getContent(CommandResult commandResult) {
        return DEFAULT_CONTENT;
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
