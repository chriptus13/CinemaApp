package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;

import java.text.MessageFormat;

public class PostTheaterCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "PostTheater";
    private static final String DEFAULT_CONTENT = "<h1>Theater Posted!</h1>\n" +
            "<p>ID: {0}</p>\n";

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
        return MessageFormat.format(DEFAULT_CONTENT, commandResult.getValue());
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
