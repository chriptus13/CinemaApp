package pt.isel.ls.control;

import pt.isel.ls.model.*;
import pt.isel.ls.model.commandResults.ExitCommandResult;
import pt.isel.ls.model.commandResults.ViewChangeCommandResult;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.DatabaseErrorException;
import pt.isel.ls.model.repositories.PostgresRepository;
import pt.isel.ls.model.utils.Pair;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.ViewEngine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class App {
    public static void main(String[] args) {
        Repository repo = new PostgresRepository();

        CommandMapper commandMapper = new CommandMapper();
        ViewEngine viewEngine = new ViewEngine();

        //Get command string from the arguments
        StringBuilder argsLine = null;
        if(args.length != 0) {
            argsLine = new StringBuilder();
            for(String s : args)
                argsLine.append(s).append(' ');
        }

        Scanner sc = new Scanner(System.in);
        List<Server> server = new LinkedList<>();
        //noinspection LoopConditionNotUpdatedInsideLoop
        do {
            try {
                String line;
                if(argsLine == null) {
                    System.out.print("Insert Command\n>");
                    line = sc.nextLine();
                } else line = argsLine.toString();

                Pair<Command, Function<String, Map<String, Object>>> command = commandMapper.fromString(Parser.getKeyCommandString(line));

                CommandResult commandResult = command.getKey().execute(Parser.getParameterMap(line, command.getValue()), repo);
                if(commandResult.getClass().equals(ViewChangeCommandResult.class)) {
                    server.add(new Server((int) commandResult.getValue(), repo, false));
                    continue;
                }
                if(commandResult.getClass().equals(ExitCommandResult.class)) {
                    System.out.println("Exiting ...");
                    break;
                }
                Map<String, String> header = Parser.getHeaderMap(line);

                CommandView view = viewEngine.getView(command.getKey().getClass().getSimpleName(), header.get("accept"));
                view.show(commandResult, header.get("file-name"));

            } catch(DatabaseErrorException e) {
                System.out.println(e.getMessage());
                break;
            } catch(CommandException e) {
                System.out.println(e.getMessage());
            }
        } while(argsLine == null);
        for(Server s : server) s.closeServer();
    }
}
