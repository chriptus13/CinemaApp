package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.view.Utils;

import java.text.MessageFormat;
import java.util.Collection;

public class GetSessionsDateInCinemaCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Sessions";
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
            // table design
            "table {\n" +
            "border-collapse: collapse;\n" +
            "width: 100%;\n" +
            "margin-top: 100px;\n" +
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
            ".not_found {\n" +
            "font-family: Impact, Charcoal, sans-serif;\n" +
            "font-size: 50px;\n" +
            "width: 100%;\n" +
            "text-align: center;\n" +
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
    private static final String DEFAULT_CONTENT = "<div class='top_bar'>\n " +
            "<h1 class='centered'>No Sessions were found</h1>\n" +
            "</div><br><br><br>\n";
    private static final String BACK_BUTTON = "<p><a href=''/cinemas/{0}/sessions/date/{1}'' class=''button''> Previous</a>" +
            "<a href=''/cinemas/{0}/sessions/date/{2}'' class=''button''> Next</a></p>" +
            "<a href=''/cinemas/{0}'' class=''button''> Back to Cinema</a>";

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
        Collection<Session> sessions = (Collection<Session>) commandResult.getValue();
        if (sessions.size() == 0) return DEFAULT_CONTENT;
        StringBuilder result = new StringBuilder("<div class='top_bar'>\n" +
                "<h1 class='centered'>Sessions</h1>\n" +
                "</div>\n" +
                "<table>\n" +
                "<tr class='table_top'>\n" +
                "<th>ID</th>\n" +
                "<th>Movie</th>\n" +
                "<th>Date</th>\n" +
                "</tr>\n");
        for (Session s : sessions)
            result.append("<tr class='hover_content'>\n<td><a href='/cinemas/").append(s.getTheater().getCinema().getId())
                    .append("/theaters/").append(s.getTheater().getId()).append("/sessions/").append(s.getId())
                    .append("'>").append(s.getId()).append("</a></td>\n").append("<td>")
                    .append(s.getMovie().getName()).append("</td>\n").append("<td>").append(s.getDate())
                    .append("</td>\n").append("</tr>\n");
        result.append("</table>\n");
        return result.toString();
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        Session s = (Session) commandResult.getDefault();
        return MessageFormat.format(BACK_BUTTON, s.getTheater().getCinema().getId(), Utils.dateToSpecialString(s.getDate(), -1),
                Utils.dateToSpecialString(s.getDate(), 1));
    }

}
