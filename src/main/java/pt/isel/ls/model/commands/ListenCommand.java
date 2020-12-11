package pt.isel.ls.model.commands;

import pt.isel.ls.model.Command;
import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.commandResults.ViewChangeCommandResult;
import pt.isel.ls.model.exceptions.CommandException;

import java.util.Map;

public class ListenCommand implements Command {
    private static final int LISTEN_PORT = 8080;

    @Override
    public CommandResult execute(Map<String, Object> args, Repository repo) throws CommandException {
        Integer portDef = (Integer) args.get("port");
        int port = portDef != null ? portDef : LISTEN_PORT;
        return new ViewChangeCommandResult(port);
    }

    @Override
    public String getDescription() {
        return "LISTEN / port=...\n" +
                "\tStarts javalet server in the specified TCP port or 8080";
    }
}
