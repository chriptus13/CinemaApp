package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class GetSessionsDateWithOptionalParamsCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 3) throw new InvalidNumberOfParametersException();
            Integer mID = (Integer) args.get("mid");
            String dmy = (String) args.get("dmy");
            args.remove("mid");
            args.remove("dmy");

            if (mID == null || dmy == null) throw new InvalidParametersException();
            Date date = Date.from(LocalDateTime.of(
                    Integer.parseInt(dmy.substring(4, 8)),
                    Integer.parseInt(dmy.substring(2, 4)),
                    Integer.parseInt(dmy.substring(0, 2)), 0, 0).
                    atZone(ZoneId.systemDefault()).toInstant());

            Session base = new Session(0, 0, mID, date);

            Map.Entry<String, Object> optional = null;
            for (Map.Entry<String, Object> e : args.entrySet()) optional = e;

            if (optional.getKey().equals("city"))
                return new MultipleCommandResult<>(repo.getSessionsWithMovieAtDateInCity(mID, date, (String) optional.getValue()), base);
            if (optional.getKey().equals("cid"))
                return new MultipleCommandResult<>(repo.getSessionsWithMovieAtDateInCinema(mID, date, (Integer) optional.getValue()), base);
            if (optional.getKey().equals("available"))
                return new MultipleCommandResult<>(repo.getSessionsWithMovieAtDateWithNAvailableSeats(mID, date, (Integer) optional.getValue()), base);
            throw new InvalidParametersException();
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /movies/{mid}/sessions/date/{dmy} city=... or cid=... or available=...\n" +
                "\tGets sessions with a certain movie at a specified data and with an additional optional parameter that can be city, cinema or number of available seats";
    }
}
