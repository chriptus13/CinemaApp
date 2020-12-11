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

public class GetSessionsDateInCinemaCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 2) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            String dmy = (String) args.get("dmy");
            if (cID == null || dmy == null || dmy.length() != 8) throw new InvalidParametersException();
            Date date = Date.from(LocalDateTime.of(
                    Integer.parseInt(dmy.substring(4, 8)),
                    Integer.parseInt(dmy.substring(2, 4)),
                    Integer.parseInt(dmy.substring(0, 2)), 0, 0).
                    atZone(ZoneId.systemDefault()).toInstant());
            return new MultipleCommandResult<>(repo.getSessionsAtDate(cID, date), new Session(cID, 0, 0, date));
        } catch (ClassCastException | NumberFormatException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinema/{cid}/sessions/date/{dmy]\n" +
                "\tGet session at a day of the year in a cinema";
    }
}
