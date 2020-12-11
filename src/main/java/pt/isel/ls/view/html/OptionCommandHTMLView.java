package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;

public class OptionCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Options";
    private static final String CSS_STYLES = "table {\n" +
            "font-family: arial, sans-serif;\n" +
            "border-collapse: collapse;\n" +
            "}\n" +
            "td ,th{\n" +
            "border: 1px solid #000000;\n" +
            "}\n" +
            "tr {\n" +
            "background-color: #FFFFFF;\n" +
            "}\n" +
            "tr:nth-child(1) {\n" +
            "background-color: #CCCCCC;\n" +
            "}\n";
    private static final String DEFAULT_CONTENT = "<h1>No Commands Found!</h1>";

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
        String options = (String) commandResult.getValue();
        if(options == null) return DEFAULT_CONTENT;
        StringBuilder result = new StringBuilder();
        result.append("<h1>Current Commands</h1>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>Command</th>\n" +
                "<th>Description</th>\n" +
                "</tr>");
        String[] test = options.split("\n|\t"); // splits on get and description, separated by
        for(int i = 0; i < test.length; i += 3) {
            result.append("<tr>\n" +
                    "<td>" + test[i] + "</td>\n" +
                    "<td>" + test[i + 2] + "</td>\n" +
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
