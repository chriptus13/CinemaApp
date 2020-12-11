package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.MultipleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

public class GetTicketsInSessionCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 3) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            Integer sID = (Integer) args.get("sid");
            if (cID == null || tID == null || sID == null)
                throw new InvalidParametersException();
            return new MultipleCommandResult<>(repo.getTicketsInSession(new Session(cID, tID, sID)), new Ticket(null, null));
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/theaters/{tid}/sessions/{sid}/tickets\n" +
                "\tGets all tickets in a session";
    }
}
