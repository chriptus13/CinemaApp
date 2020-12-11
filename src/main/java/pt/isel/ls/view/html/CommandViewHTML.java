package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.utils.Pair;
import pt.isel.ls.view.CommandView;

import java.text.MessageFormat;
import java.util.List;

public abstract class CommandViewHTML extends CommandView {
    private static final String HTML_BASE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>{0}</title>\n" +    // Page Title
            "</head>\n" +
            "<style media='screen'>" +
            "{1}" +
            "</style>\n" +              // Styles
            "<body>\n" +
            "{2}" +                     // Content to display
            "<br>\n" +
            "{3}" +                     // Back button
            "</body>\n" +
            "</html>";

    public static String mergeHTMLs(List<Pair<CommandViewHTML, CommandResult>> list) {
        StringBuilder styles = new StringBuilder(), content = new StringBuilder();
        for(Pair<CommandViewHTML, CommandResult> pair : list) {
            styles.append(pair.getKey().getCSSStyles());
            content.append(pair.getKey().getContent(pair.getValue()));
        }
        Pair<CommandViewHTML, CommandResult> first = list.get(0);
        return MessageFormat.format(HTML_BASE, first.getKey().getTitle(first.getValue()), styles.toString(), content.toString(), first.getKey().getBackButton(first.getValue()));
    }

    @Override
    public String getStringToPrint(CommandResult commandResult) {
        return MessageFormat.format(HTML_BASE, getTitle(commandResult), getCSSStyles(), getContent(commandResult), getBackButton(commandResult));
    }

    // Gets page Title
    public abstract String getTitle(CommandResult commandResult);

    // Gets page CSS Styles
    public abstract String getCSSStyles();

    // Gets page Content for specified command result
    public abstract String getContent(CommandResult commandResult);

    // Gets back button
    public abstract String getBackButton(CommandResult commandResult);
}
