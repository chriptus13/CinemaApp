package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;

public class GetAvailableTicketsInSessionCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Available Tickets";

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
        return "<h1>Availability</h1>\n" +
                "<p>There's currently " + commandResult.getValue().toString() + " seats available</p>\n";
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
