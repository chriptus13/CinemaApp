package pt.isel.ls.model;

import pt.isel.ls.model.commands.DeleteTicketsCommand;
import pt.isel.ls.model.commands.ExitCommand;
import pt.isel.ls.model.commands.ListenCommand;
import pt.isel.ls.model.commands.OptionCommand;
import pt.isel.ls.model.commands.get.*;
import pt.isel.ls.model.commands.post.*;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CommandMapper {
    private Map<Pattern, Pair<Command, Function<String, Map<String, Object>>>> commands = new HashMap<>();

    /**
     * Checks if the string 'str' contains the pattern of any command registered
     *
     * @param str Input string
     * @return Map entry where the pattern matches the string 'str'
     * @throws CommandException if there's no command with string 'str' pattern
     */
    public Pair<Command, Function<String, Map<String, Object>>> fromString(String str) throws CommandException {
        for(Map.Entry<Pattern, Pair<Command, Function<String, Map<String, Object>>>> e : commands.entrySet())
            if(e.getKey().matcher(str).matches()) return e.getValue();
        throw new CommandException("Invalid command");
    }

    public CommandMapper() {
        registerCommands();
    }

    /**
     * Command registration into the map
     */
    private void registerCommands() {
        commands.put(Pattern.compile("GET /"), new Pair<>(new IndexCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("POST /movies"), new Pair<>(new PostMovieCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("GET /movies"), new Pair<>(new GetMoviesCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("GET /movies/[0-9]+"), new Pair<>(new GetMovieCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("mid", Integer.parseInt(aux[2]));
            return res;
        }));

        commands.put(Pattern.compile("POST /cinemas"), new Pair<>(new PostCinemaCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("POST /cinemas/[0-9]+/theaters"), new Pair<>(new PostTheaterCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas"), new Pair<>(new GetCinemasCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+"), new Pair<>(new GetCinemaCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters"), new Pair<>(new GetTheatersCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[a-zA-Z0-9-_]+/theaters/[a-zA-Z0-9-_]+"), new Pair<>(new GetTheaterCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            return res;
        }));

        commands.put(Pattern.compile("POST /cinemas/[0-9]+/theaters/[0-9]+/sessions"), new Pair<>(new PostSessionCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions"), new Pair<>(new GetSessionsInTheaterCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/sessions"), new Pair<>(new GetSessionsInCinemaCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/sessions/[0-9]+"), new Pair<>(new GetSessionCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("sid", Integer.parseInt(aux[4]));
            return res;
        }));

        //new thingy for phase3
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+"), new Pair<>(new GetSessionCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("sid", Integer.parseInt(aux[6]));
            return res;
        }));
        //new thingy for phase3
        commands.put(Pattern.compile("GET /movies/[0-9]+/sessions"), new Pair<>(new GetSessionsWithMovieCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("mid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/sessions/today"), new Pair<>(new GetSessionsTodayInCinemaCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));

        //added in phase 3,yes it the the same as the function on top
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/sessions/date/today"), new Pair<>(new GetSessionsTodayInCinemaCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            return res;
        }));
        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions/today"), new Pair<>(new GetSessionsTodayInTheaterCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            return res;
        }));

        commands.put(Pattern.compile("GET /cinemas/[0-9]+/sessions/date/[0-9]+"), new Pair<>(new GetSessionsDateInCinemaCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("dmy", aux[5]);
            return res;
        }));

        commands.put(Pattern.compile("POST /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+/tickets"), new Pair<>(new PostTicketCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            res.put("sid", Integer.parseInt(aux[6]));
            return res;
        }));

        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+/tickets"), new Pair<>(new GetTicketsInSessionCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            res.put("sid", Integer.parseInt(aux[6]));
            return res;
        }));

        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+/tickets/[A-Za-z0-9]+"), new Pair<>(new GetTicketCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            res.put("sid", Integer.parseInt(aux[6]));
            res.put("tkid", aux[8]);
            return res;
        }));

        commands.put(Pattern.compile("GET /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+/tickets/available"), new Pair<>(new GetAvailableTicketsInSessionCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            res.put("sid", Integer.parseInt(aux[6]));
            return res;
        }));

        commands.put(Pattern.compile("DELETE /cinemas/[0-9]+/theaters/[0-9]+/sessions/[0-9]+/tickets"), new Pair<>(new DeleteTicketsCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("cid", Integer.parseInt(aux[2]));
            res.put("tid", Integer.parseInt(aux[4]));
            res.put("sid", Integer.parseInt(aux[6]));
            return res;
        }));

        commands.put(Pattern.compile("GET /movies/[0-9]+/sessions/date/[0-9]+"), new Pair<>(new GetSessionsDateWithOptionalParamsCommand(), str -> {
            String[] aux = str.split("/");
            HashMap<String, Object> res = new HashMap<>();
            res.put("mid", Integer.parseInt(aux[2]));
            res.put("dmy", aux[5]);
            return res;
        }));

        commands.put(Pattern.compile("EXIT /"), new Pair<>(new ExitCommand(), str -> new HashMap<>()));
        commands.put(Pattern.compile("OPTION /"), new Pair<>(new OptionCommand(), str -> {
            Map<String, Object> res = new HashMap<>();
            commands.forEach((pattern, commandFunctionPair) -> res.put(commandFunctionPair.getKey().getClass().getSimpleName(), commandFunctionPair.getKey().getDescription()));
            return res;
        }));
        commands.put(Pattern.compile("LISTEN /"), new Pair<>(new ListenCommand(), str -> new HashMap<>()));
    }
}
