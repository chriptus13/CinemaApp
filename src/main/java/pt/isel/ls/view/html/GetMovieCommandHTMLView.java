package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Movie;

public class GetMovieCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Movie - ";
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
    private static final String BACK_BUTTON = "<a href='/movies' class='button'>Back to movies</a>\n";

    @Override
    public String getTitle(CommandResult commandResult) {
        Movie m = (Movie) commandResult.getValue();
        return TITLE + m.getName();
    }

    @Override
    public String getCSSStyles() {
        return CSS_STYLES;
    }

    @Override
    public String getContent(CommandResult commandResult) {
        Movie m = (Movie) commandResult.getValue();
        return "<div class='top_bar'>\n" +
                "<h1 class='centered'>Movie</h1>\n" +
                "</div>\n" +
                "<div style='margin-top: 100px'>\n" +
                "<p class='pargh'>ID: " + m.getId() + "</p>\n" +
                "<p class='pargh'>Name: " + m.getName() + "</p>\n" +
                "<p class='pargh'>Duration: " + m.getDuration() + "</p>\n" +
                "<p class='pargh'>Release Year: " + m.getReleaseYear() + "</p>\n" +
                "</div>\n";
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return BACK_BUTTON;
    }
}
