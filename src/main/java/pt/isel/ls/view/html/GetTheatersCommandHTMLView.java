package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Theater;

import java.util.Collection;

public class GetTheatersCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Theaters";
    private static final String CSS_STYLES = ".centered {\n" +
            "text-align: center;\n" +
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
            ".not_found {\n" +
            "font-family: Impact, Charcoal, sans-serif;\n" +
            "font-size: 50px;\n" +
            "width: 100%;\n" +
            "text-align: center;\n" +
            "}\n";

    private static final String DEFAULT_CONTENT = "<h1 class='not_found'>No Theaters were found!</h1>\n";

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
        Collection<Theater> theaters = (Collection<Theater>) commandResult.getValue();
        if (theaters.size() == 0) return DEFAULT_CONTENT;
        StringBuilder result = new StringBuilder("<div><h1 class='centered'>Theaters</h1>\n" +
                "</div>\n" +
                "<table>\n" +
                "<tr class='table_top'>\n" +
                "<th>ID</th>\n" +
                "<th>Name</th>\n" +
                "<th>Available Seats</th>\n" +
                "</tr>\n");
        for (Theater t : theaters)
            result.append("<tr class='hover_content'>\n<td><a href='/cinemas/").append(t.getCinema().getId())
                    .append("/theaters/").append(t.getId())
                    .append("'>").append(t.getId()).append("</a></td>\n").append("<td>")
                    .append(t.getName()).append("</td>\n").append("<td>").append(t.getAvailableSeats())
                    .append("</td>\n").append("</tr>\n");
        result.append("</table>\n");
        return result.toString();
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
