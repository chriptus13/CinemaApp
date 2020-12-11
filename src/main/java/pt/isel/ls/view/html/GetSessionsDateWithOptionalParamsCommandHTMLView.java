package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Session;

import java.util.Collection;

public class GetSessionsDateWithOptionalParamsCommandHTMLView extends CommandViewHTML {
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
    private static final String HTML_DEFAULT_CONTENT = "<h1>Sessions</h1>\n" +
            "<p>No Sessions were found!</p>\n";

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
        if(sessions.size() == 0) return HTML_DEFAULT_CONTENT;
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Session s : sessions) {
            if(first) {
                result.append("<h1>Cinema </h1>" +
                        "<p> Name =" + s.getTheater().getCinema().getName() + "</p>\n" +
                        "<p> Date =" + s.getDate().toString() + "</p>\n" +
                        "<h2>Sessions Table</h2>\n" +
                        "<table>\n" +
                        "<tr>\n" +
                        "<th>ID</th>\n" +
                        "<th>Theater</th>\n" +
                        "<th>Movie</th>\n" +
                        "<th>Maximum Available Seats</th>\n" +
                        "</tr>\n");
                first = false;
            }
            result.append("<tr>\n" +
                    "<td>" + s.getId() + "</td>\n" +
                    "<td>" + s.getTheater().getName() + "</td>\n" +
                    "<td>" + s.getMovie().getName() + "</td>\n" +
                    "<td>" + s.getTheater().getAvailableSeats() + "</td>\n" +
                    "</tr>\n");
        }
        result.append("</table>\n");
        return result.toString();
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
