package pt.isel.ls.model.commands.post;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidDataFormatException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Command to insert a new Session
 */
public class PostSessionCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 4) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            Integer mID = (Integer) args.get("mid");
            String dateS = ((String) args.get("date"));
            if (dateS == null)
                throw new InvalidDataFormatException();
            String[] buffer = dateS.split("-| ");
            if (buffer.length != 5)
                throw new InvalidDataFormatException();
            Date date = Date.from(LocalDateTime.of(
                    Integer.parseInt(buffer[0]),
                    Integer.parseInt(buffer[1]),
                    Integer.parseInt(buffer[2]),
                    Integer.parseInt(buffer[3]),
                    Integer.parseInt(buffer[4])).
                    atZone(ZoneId.systemDefault()).toInstant());
            if (cID == null || tID == null || mID == null)
                throw new InvalidParametersException();
            int id = repo.insertSession(new Session(cID, tID, mID, date));
            return new SingleCommandResult<>(id);
        } catch (ClassCastException | NumberFormatException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "POST /cinemas/{cid}/theaters/{tid}/sessions mid=...&date=...\n" +
                "\tAdds a session to a theater in database";
    }
}
