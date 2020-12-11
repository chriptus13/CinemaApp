package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;

import java.text.MessageFormat;

public class GetCinemaCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Cinema - ";
    private static final String CSS_STYLES = ".top_bar {\n" +
            "background-color: #437512;\n" +
            "width: 100%;\n" +
            "position: fixed;\n" +
            "z-index: 1;\n" +
            "top: 0;\n" +
            "overflow-x: hidden;\n" +
            "left: 0;\n" +
            "font-family: Impact, Charcoal, sans-serif;\n" +
            "}\n" +
            ".centered {\n" +
            "text-align: center;\n" +
            "}\n" +
            ".pargh {\n" +
            "text-align: center;\n" +
            "font-family: Arial, Helvetica, sans-serif;\n" +
            "}\n" +
            "a.button {\n" +
            "background-color: white;\n" +
            "border: 2px solid #437512;\n" +
            "color: black;\n" +
            "padding: 8px 16px;\n" +
            "text-align: center;\n" +
            "text-decoration: none;\n" +
            "display: inline-block;\n" +
            "font-size: 16px;\n" +
            "margin: 4px 2px;\n" +
            "transition-duration: 0.4s;\n" +
            "cursor: pointer;\n" +
            "}\n" +
            "a.button:hover {\n" +
            "background-color: #437512;\n" +
            "color: white;\n" +
            "}\n";
    private static final String BACK_BUTTON = "<p><a href=''/cinemas/{0}/sessions/date/today'' class=''button''>Sessions Today</a></p>\n" +
            "<a href=''/cinemas'' class=''button''>Back to cinemas</a>";
    private static final String FORM = "<form method=''post'' action=''/cinemas/{0}/theaters''>\n" +
            "<p>Name: <input type=''text'' name=''name''></p>\n" +
            "<p>Rows: <input type=''text'' name=''rows''></p>\n" +
            "<p>Seats:<input type=''text'' name=''seats''></p>\n" +
            "<input type=''submit'' value=''Submit''>\n" +
            "</form>\n";

    @Override
    public String getTitle(CommandResult commandResult) {
        Cinema c = (Cinema) commandResult.getValue();
        return TITLE + c.getName();
    }

    @Override
    public String getCSSStyles() {
        return CSS_STYLES;
    }

    @Override
    public String getContent(CommandResult commandResult) {
        Cinema c = (Cinema) commandResult.getValue();
        return "<div class='top_bar'>\n" +
                "<h1 class='centered'>Cinema</h1>\n" +
                "</div>\n" +
                "<div style='margin-top: 100px'>\n" +
                "<p class='pargh'>ID: " + c.getId() + "</p>\n" +
                "<p class='pargh'>Name: " + c.getName() + "</p>\n" +
                "<p class='pargh'>City: " + c.getCity() + "</p>\n" +
                "</div>\n" +
                MessageFormat.format(FORM, c.getId());
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        Cinema c = (Cinema) commandResult.getValue();
        return MessageFormat.format(BACK_BUTTON, c.getId());
    }
}
