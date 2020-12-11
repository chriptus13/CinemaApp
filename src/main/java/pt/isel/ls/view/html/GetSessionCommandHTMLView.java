package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;

import java.text.MessageFormat;

public class GetSessionCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Session";
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
            "a.button: hover {\n" +
            "background-color: #437512;\n" +
            "color: white;\n" +
            "}\n";

    private static final String BACK_BUTTON = "<a href=''/cinemas/{0}/sessions/today'' class=''button''>Today</a>\n";
    private static final String FORM = "<form method=''post'' action=''/cinemas/{0}/theaters/{1}/sessions/{2}/tickets''>\n" +
            "<p>Row: <input type=''text'' name=''row''></p>\n" +
            "<p>Seat: <input type=''text'' name=''seat''></p>\n" +
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
        Session s = (Session) commandResult.getValue();
        return "<div class='top_bar'>\n" +
                "<h1 class='centered'>Session</h1>\n" +
                "</div>\n" +
                "<div style='margin-top: 100px'>\n" +
                "<p class='pargh'>ID: " + s.getId() + "</p>\n" +
                "<p class='pargh'><a href='/movies/" + s.getMovie().getId() + "' class='button'> Movie: " + s.getMovie().getName() + "</a></p>\n" +
                "<p class='pargh'><a href='/cinemas/" + s.getTheater().getCinema().getId() + "/theaters/" + s.getTheater().getId() + "' class='button'> Theater: " + s.getTheater().getName() + "</a></p>\n" +
                "<p class='pargh'>Available Seats: " + s.getTheater().getAvailableSeats() + "</p>\n" +
                "</div>\n" +
                MessageFormat.format(FORM, s.getTheater().getCinema().getId(),
                        s.getTheater().getId(), s.getId());
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        Session s = (Session) commandResult.getValue();
        return MessageFormat.format(BACK_BUTTON, s.getTheater().getCinema().getId());
    }
}
