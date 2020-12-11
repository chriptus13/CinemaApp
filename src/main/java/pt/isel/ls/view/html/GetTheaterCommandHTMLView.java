package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;

import java.text.MessageFormat;

public class GetTheaterCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Theater";
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
            // table design
            "table {\n" +
            "border-collapse: collapse;\n" +
            "width: 100%;\n" +
            "font-family: Arial, Helvetica, sans-serif;\n" +
            "}\n" +
            "th, td {\n" + "padding: 8px;\n" +
            "text-align: left;\n" +
            "border-bottom: 1px solid #ddd;\n" + "}\n" +
            ".hover_content:hover {\n" +
            "background-color: #e5f5b7;\n" +
            "}\n" +
            ".hover_content a {\n" +
            "display: block;\n" +
            "width: 100%;\n" +
            "height: 100%;\n" +
            "color: #062400;\n" +
            "text-decoration: none;\n" +
            "}\n" +
            ".table_top {\n" +
            "background-color: #437512;\n" +
            "font-family: Impact, Charcoal, sans-serif;\n" +
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
    private static final String BACK_BUTTON = "<a href=''/cinemas/{0}'' class=''button''>Back to cinema</a>\n";
    private static final String FORM = "<form method=''post'' action=''/cinemas/{0}/theaters/{1}/sessions''>\n" +
            "<p>Date(YYYY-MM-DD HH-MM): <input type=''text'' name=''date''></p>\n" +
            "<p>Movie ID: <input type=''text'' name=''mid''></p>\n" +
            "<input type=''submit'' value=''Submit''>\n" +
            "</form>\n";
    @Override
    public String getTitle(CommandResult commandResult) {
        return TITLE;
    }

    @Override
    public String getCSSStyles() {
        return CSS_STYLES;
    }

    @Override
    public String getContent(CommandResult commandResult) {
        Theater t = (Theater) commandResult.getValue();
        return "<div class='top_bar'>\n" +
                "<h1 class='centered'>Theater</h1>\n" +
                "</div>\n" +
                "<div style='margin-top: 100px'>\n" +
                "<p class='pargh'>ID: " + t.getId() + "</p>\n" +
                "<p class='pargh'>Name: " + t.getName() + "</p>\n" +
                "<p class='pargh'>Seats: " + t.getNSeatsPerRow() + "</p>\n" +
                "<p class='pargh'>Rows: " + t.getNRows() + "</p>\n" +
                "</div>\n" +
                MessageFormat.format(FORM, t.getCinema().getId(), t.getId());
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return MessageFormat.format(BACK_BUTTON, ((Theater) commandResult.getValue()).getCinema().getId());
    }
}
