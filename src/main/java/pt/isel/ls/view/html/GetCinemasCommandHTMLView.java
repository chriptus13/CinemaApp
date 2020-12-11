package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Cinema;

import java.util.Collection;

public class GetCinemasCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Cinemas";
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
    private static final String DEFAULT_CONTENT = "<h1 class='not_found'>No Cinemas were found</h1>";
    private static final String FORM = "<form method='post' action='/cinemas'>\n" +
            "<p>Name: <input type='text' name='name'></p>\n" +
            "<p>City: <input type='text' name='city'></p>\n" +
            "<input type='submit' value='Submit'>\n" +
            "</form>\n";
    private static final String BACK_BUTTON = "<a href='/' class='button'>Back to home</a>\n";

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
        Collection<Cinema> cinemas = (Collection<Cinema>) commandResult.getValue();
        if (cinemas.size() == 0)
            return DEFAULT_CONTENT+FORM;
        StringBuilder result = new StringBuilder("<div class='top_bar'>\n" +
                "<h1 class='centered'>Cinemas</h1>\n" +
                "</div>\n" +
                "<table>\n" +
                "<tr class='table_top'>\n" +
                "<th>ID</th>\n" +
                "<th>Name</th>\n" +
                "<th>City</th>\n" +
                "</tr>\n");
        for (Cinema c : cinemas)
            result.append("<tr class='hover_content'>\n<td><a href='/cinemas/").append(c.getId())
                    .append("'>").append(c.getId()).append("</a></td>\n").append("<td>")
                    .append(c.getName()).append("</td>\n").append("<td>").append(c.getCity())
                    .append("</td>\n").append("</tr>\n");
        result.append("</table>\n").append(FORM);
        return result.toString();
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return BACK_BUTTON;
    }
}
