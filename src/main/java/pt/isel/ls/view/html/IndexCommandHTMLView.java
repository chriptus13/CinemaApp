package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;

public class IndexCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Grupo 06 - Index";
    private static final String CSS_STYLES =
            // Split the screen in half
            ".split {\n" +
                    "height: 100%;\n" +
                    "width: 50%;\n" +
                    "position: fixed;\n" +
                    "z-index: 1;\n" +
                    "top: 0;\n" +
                    "overflow-x: hidden;\n" +
                    "padding-top: 20px;\n" +
                    "}\n" +
                    // Control the left side
                    ".left {\n" +
                    "left: 0;\n" +
                    "background-color: #c3da8c;\n" +
                    "}\n" +
                    // Control the right side
                    ".right {\n" +
                    "right: 0;\n" +
                    "background-color: #e5f5b7;\n" +
                    "}\n" +
                    // If you want the content centered horizontally and vertically
                    ".centered {\n" +
                    "position: absolute;\n" +
                    "top: 50%;\n" +
                    "left: 50%;\n" +
                    "transform: translate(-50%, -50%);\n" +
                    "text-align: center;\n" +
                    "}\n" +
                    // Style the image inside the centered container, if needed
                    ".centered img {\n" +
                    "width: 150px;\n" +
                    "border-radius: 50%;\n" +
                    "}\n" +
                    // Tag <a> mutch prity
                    ".words {\n" +
                    "display: block;\n" +
                    "width: 100%;\n" +
                    "height: 100%;\n" +
                    "color: #062400;\n" +
                    "font-size: 40px;\n" +
                    "text-decoration: none;\n" +
                    "font-family:Impact, Charcoal, sans-serif;\n" +
                    "}\n" +
                    // hover each half
                    ".left:hover {\n" +
                    "background-color: #437512;\n" +
                    "}\n" +
                    ".right:hover {\n" +
                    "background-color: #437512;\n" +
                    "}\n";
    private static final String HTML_CONTENT = "<div class=\"split left\">\n" +
            "<a class=\"words\" href='/movies'>\n" +
            "<div class=\"centered\">\n" +
            "<h1>Movies</h1>\n" +
            "</div>\n" +
            "</a>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"split right\">\n" +
            "<a class=\"words\" href='/cinemas'>\n" +
            "<div class=\"centered\">\n" +
            "<h1>Cinemas</h1>\n" +
            "</div>\n" +
            "</a>\n" +
            "</div>\n";

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
        return HTML_CONTENT;
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
