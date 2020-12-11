package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;

import java.util.Collection;

public class GetSessionsTodayInTheaterCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Sessions";
    private static final String CSS_STYLES = "table {\n" +
            "font-family: arial, sans-serif;\n" +
            "border-collapse: collapse;\n" +
            "}\n" +
            "td, th {\n" +
            "border: 1px solid #000000;\n" +
            "}\n" +
            "tr:nth-child(1) {\n" +
            "background-color: #CCCCCC;\n" +
            "}\n";
    private static final String HTML_DEFAULT_CONTENT = "<h1>Sessions</h1><p>No Sessions were found!</p>\n";

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
        if (sessions.size() == 0) return HTML_DEFAULT_CONTENT;
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
        return "";
    }
}
