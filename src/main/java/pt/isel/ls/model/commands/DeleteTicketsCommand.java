package pt.isel.ls.model.commands;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.InvalidFormatTkID;
import pt.isel.ls.model.exceptions.InvalidNumberOfParametersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteTicketsCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 4) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            Integer sID = (Integer) args.get("sid");
            Object tkID = args.get("tkid");
            List<String> tickets;
            if (tkID instanceof String) {
                tickets = new ArrayList<>();
                tickets.add((String) tkID);
            } else tickets = (List<String>) args.get("tkid");

            if (cID == null || tID == null || sID == null)
                throw new InvalidParametersException();
            for (String s : tickets)
                if (!Ticket.isTkID(s)) throw new InvalidFormatTkID();
            repo.deleteTicket(new Session(cID, tID, sID), tickets);
            return new SingleCommandResult<>(-1);
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "DELETE /cinemas/{cid}/theaters/{tid}/sessions/{sid}/tickets tkid=...&...\n" +
                "\tDeletes the tickets";
    }
}
