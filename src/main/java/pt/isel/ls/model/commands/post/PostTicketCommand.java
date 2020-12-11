package pt.isel.ls.model.commands.post;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.Map;

public class PostTicketCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 5) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            Integer sID = (Integer) args.get("sid");
            String row = (String) args.get("row");
            Integer seat = (Integer) args.get("seat");
            if (cID == null || tID == null || sID == null || seat == null || row == null || row.length() == 0)
                throw new InvalidParametersException();
            repo.insertTicket(new Ticket(new Session(cID, tID, sID), row.charAt(0), seat));
            return new SingleCommandResult<>(row + seat);
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "POST /cinemas/{cid}/theaters/{tid}/sessions/{sid}/tickets row=...&seat=...\n" +
                "\tAdds a ticket to a sessions in database";
    }
}
