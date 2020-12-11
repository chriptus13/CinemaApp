package pt.isel.ls.model.commands.get;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.SingleCommandResult;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.exceptions.*;

import java.util.Map;

public class GetTicketCommand implements Command {

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        try {
            if (args.size() != 4) throw new InvalidNumberOfParametersException();
            Integer cID = (Integer) args.get("cid");
            Integer tID = (Integer) args.get("tid");
            Integer sID = (Integer) args.get("sid");
            String tkID = (String) args.get("tkid");

            if (cID == null || tID == null || sID == null || tkID == null)
                throw new InvalidParametersException();

            //Check tkID legibility
            String aux = tkID.substring(0, 1);
            if (aux.charAt(0) < 'A' || aux.charAt(0) > 'Z') throw new InvalidRowException();
            aux = tkID.substring(1);
            Integer.parseInt(aux);

            return new SingleCommandResult<>(repo.getTicket(new Session(cID, tID, sID), tkID));
        } catch (NumberFormatException e) {
            throw new InvalidSeatException();
        } catch (ClassCastException e) {
            throw new InvalidParametersException();
        }
    }

    @Override
    public String getDescription() {
        return "GET /cinemas/{cid}/theaters/{tid}/sessions/{sid}/tickets/{tkid}\n" +
                "\tGets a ticket and the information about the session";
    }
}
