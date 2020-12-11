package pt.isel.ls.view;

import pt.isel.ls.model.exceptions.InvalidAcceptException;
import pt.isel.ls.view.html.*;
import pt.isel.ls.view.json.*;
import pt.isel.ls.view.plain.*;

import java.util.HashMap;

public class ViewEngine {
    private HashMap<String, CommandView> viewMap = new HashMap<>();

    private void registerViews() {
        // PLAIN
        viewMap.put("GetCinemaCommand_text/plain", new GetCinemaCommandPlainView());
        viewMap.put("GetCinemasCommand_text/plain", new GetCinemasCommandPlainView());
        viewMap.put("GetMovieCommand_text/plain", new GetMovieCommandPlainView());
        viewMap.put("GetMoviesCommand_text/plain", new GetMoviesCommandPlainView());
        viewMap.put("GetSessionCommand_text/plain", new GetSessionCommandPlainView());
        viewMap.put("GetSessionsInCinemaCommand_text/plain", new GetSessionsInCinemaCommandPlainView());
        viewMap.put("GetSessionsInTheaterCommand_text/plain", new GetSessionsInTheaterCommandPlainView());
        viewMap.put("GetSessionsTodayInCinemaCommand_text/plain", new GetSessionsTodayInCinemaCommandPlainView());
        viewMap.put("GetSessionsTodayInTheaterCommand_text/plain", new GetSessionsTodayInTheaterCommandPlainView());
        viewMap.put("GetTheaterCommand_text/plain", new GetTheaterCommandPlainView());
        viewMap.put("GetTheatersCommand_text/plain", new GetTheatersCommandPlainView());
        viewMap.put("PostCinemaCommand_text/plain", new PostCinemaCommandPlainView());
        viewMap.put("PostTheaterCommand_text/plain", new PostTheaterCommandPlainView());
        viewMap.put("PostSessionCommand_text/plain", new PostSessionCommandPlainView());
        viewMap.put("PostMovieCommand_text/plain", new PostMovieCommandPlainView());
        viewMap.put("GetSessionsDateInCinemaCommand_text/plain", new GetSessionsDateInCinemaCommandPlainView());
        viewMap.put("PostTicketCommand_text/plain", new PostTicketCommandPlainView());
        viewMap.put("GetTicketsInSessionCommand_text/plain", new GetTicketsInSessionCommandPlainView());
        viewMap.put("GetTicketCommand_text/plain", new GetTicketCommandPlainView());
        viewMap.put("GetAvailableTicketsInSessionCommand_text/plain", new GetAvailableTicketsInSessionCommandPlainView());
        viewMap.put("GetSessionsDateWithOptionalParamsCommand_text/plain", new GetSessionsDateWithOptionalParamsCommandPlainView());
        viewMap.put("DeleteTicketsCommand_text/plain", new DeleteTicketsCommandPlainView());
        viewMap.put("OptionCommand_text/plain", new OptionCommandPlainView());

        // html
        viewMap.put("GetCinemaCommand_text/html", new GetCinemaCommandHTMLView());
        viewMap.put("GetCinemasCommand_text/html", new GetCinemasCommandHTMLView());
        viewMap.put("GetMovieCommand_text/html", new GetMovieCommandHTMLView());
        viewMap.put("GetMoviesCommand_text/html", new GetMoviesCommandHTMLView());
        viewMap.put("GetSessionCommand_text/html", new GetSessionCommandHTMLView());
        viewMap.put("GetSessionsInCinemaCommand_text/html", new GetSessionsInCinemaCommandHTMLView());
        viewMap.put("GetSessionsInTheaterCommand_text/html", new GetSessionsInTheaterCommandHTMLView());
        viewMap.put("GetSessionsTodayInCinemaCommand_text/html", new GetSessionsTodayInCinemaCommandHTMLView());
        viewMap.put("GetSessionsTodayInTheaterCommand_text/html", new GetSessionsTodayInTheaterCommandHTMLView());
        viewMap.put("GetTheaterCommand_text/html", new GetTheaterCommandHTMLView());
        viewMap.put("GetTheatersCommand_text/html", new GetTheatersCommandHTMLView());
        viewMap.put("PostCinemaCommand_text/html", new PostCinemaCommandHTMLView());
        viewMap.put("PostTheaterCommand_text/html", new PostTheaterCommandHTMLView());
        viewMap.put("PostSessionCommand_text/html", new PostSessionCommandHTMLView());
        viewMap.put("PostMovieCommand_text/html", new PostMovieCommandHTMLView());
        viewMap.put("GetSessionsDateInCinemaCommand_text/html", new GetSessionsDateInCinemaCommandHTMLView());
        viewMap.put("PostTicketCommand_text/html", new PostTicketCommandHTMLView());
        viewMap.put("GetTicketsInSessionCommand_text/html", new GetTicketsInSessionCommandHTMLView());
        viewMap.put("GetTicketCommand_text/html", new GetTicketCommandHTMLView());
        viewMap.put("GetAvailableTicketsInSessionCommand_text/html", new GetAvailableTicketsInSessionCommandHTMLView());
        viewMap.put("GetSessionsDateWithOptionalParamsCommand_text/html", new GetSessionsDateWithOptionalParamsCommandHTMLView());
        viewMap.put("DeleteTicketsCommand_text/html", new DeleteTicketsCommandHTMLView());
        viewMap.put("OptionCommand_text/html", new OptionCommandHTMLView());
        viewMap.put("IndexCommand_text/html", new IndexCommandHTMLView());
        viewMap.put("GetSessionsWithMovieCommand_text/html", new GetSessionsWithMovieCommandHTMLView());

        // json
        viewMap.put("GetCinemaCommand_application/json", new GetCinemaCommandJSONView());
        viewMap.put("GetCinemasCommand_application/json", new GetCinemasCommandJSONView());
        viewMap.put("GetMovieCommand_application/json", new GetMovieCommandJSONView());
        viewMap.put("GetMoviesCommand_application/json", new GetMoviesCommandJSONView());
        viewMap.put("GetSessionCommand_application/json", new GetSessionCommandJSONView());
        viewMap.put("GetSessionsDateWithOptionalParamsCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("GetSessionsInCinemaCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("GetSessionsInTheaterCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("GetSessionsTodayInCinemaCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("GetSessionsTodayInTheaterCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("GetTheaterCommand_application/json", new GetTheaterCommandJSONView());
        viewMap.put("GetTheatersCommand_application/json", new GetTheatersCommandJSONView());
        viewMap.put("PostCinemaCommand_application/json", new PostCinemaCommandJSONView());
        viewMap.put("PostTheaterCommand_application/json", new PostTheaterCommandJSONView());
        viewMap.put("PostSessionCommand_application/json", new PostSessionCommandJSONView());
        viewMap.put("PostMovieCommand_application/json", new PostMovieCommandJSONView());
        viewMap.put("GetSessionsDateInCinemaCommand_application/json", new GetSessionsCommandJSONView());
        viewMap.put("PostTicketCommand_application/json", new PostTicketCommandJSONView());
        viewMap.put("GetTicketsInSessionCommand_application/json", new GetTicketsInSessionCommandJSONView());
        viewMap.put("GetTicketCommand_application/json", new GetTicketCommandJSONView());
        viewMap.put("GetAvailableTicketsInSessionCommand_application/json", new GetAvailableTicketsInSessionCommandJSONView());
        viewMap.put("DeleteTicketsCommand_application/json", new DeleteTicketsCommandJSONView());
        viewMap.put("OptionCommand_application/json", new OptionCommandJSONView());
    }

    public ViewEngine() {
        this.registerViews();
    }

    public CommandView getView(String commandName, String accept) throws InvalidAcceptException {
        String key;
        if (accept == null) key = commandName + "_text/html";
        else key = commandName + '_' + accept;
        if (!viewMap.containsKey(key)) throw new InvalidAcceptException();
        return viewMap.get(key);
    }
}
