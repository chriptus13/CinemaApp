package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;

import java.text.MessageFormat;

public class GetTicketCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Ticket";
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

    private static final String BACK_BUTTON = "<a href=''/cinemas/{0}/theaters/{1}/sessions/{2}'' class=''button''>Back to session</a>\n";

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
        Ticket t = (Ticket) commandResult.getValue();
        return "<div class='top_bar'>\n" +
                "<h1 class='centered'>Ticket</h1>\n" +
                "</div>\n" +
                "<div style='margin-top: 100px'>\n" +
                "<p class='pargh'>Row: " + t.getRowInteger() + "</p>\n" +
                "<p class='pargh'>Seat: " + t.getRowInteger() + "</p>\n" +
                "<p class='pargh'>Date: " + t.getSession().getDate().toString() + "</p>\n" +
                "<p class='pargh'>Movie: " + t.getSession().getMovie().getName() + "</p>\n" +
                "<p class='pargh'>Theater: " + t.getSession().getTheater().getName() + "</p>\n" +
                "</div>\n";
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        Ticket t = (Ticket) commandResult.getValue();
        return MessageFormat.format(BACK_BUTTON,
                t.getSession().getTheater().getCinema().getId(), t.getSession().getTheater().getId(), t.getSession().getId());
    }
}
