package pt.isel.ls.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.model.*;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.utils.Pair;
import pt.isel.ls.view.CommandView;
import pt.isel.ls.view.ViewEngine;
import pt.isel.ls.view.html.CommandViewHTML;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ServletGen extends HttpServlet {
    private static final Logger _logger = LoggerFactory.getLogger(ServletGen.class);

    private CommandMapper commandMapper = new CommandMapper();
    private ViewEngine viewEngine = new ViewEngine();
    private Repository repository;

    ServletGen(Repository repo) {
        repository = repo;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        _logger.info("{} on '{}' with accept:'{}', instance {}",
                req.getMethod(), req.getRequestURI(), req.getHeader("Accept"),
                this.hashCode());
        String aux = req.getMethod() + " " + req.getRequestURI();

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format(req.getHeader("Accept"), utf8.name()));

        Pair<Command, Function<String, Map<String, Object>>> command;
        String ret;

        try {
            command = commandMapper.fromString(Parser.getKeyCommandString(aux));

            CommandResult commandResult = command.getKey().execute(Parser.getParameterMap(aux, command.getValue()), repository);
            Map<String, String> header = Parser.getHeaderMap(req.getHeader("Accept").split("/")[0]);

            if(!req.getHeader("Accept").contains("text/html")) {
                CommandView view = viewEngine.getView(command.getKey().getClass().getSimpleName(), header.get("accept"));
                ret = view.getStringToPrint(commandResult);
            } else {
                List<Pair<CommandViewHTML, CommandResult>> list = new LinkedList<>();
                for(String s : AdditionalInfo.translator(command.getKey().getClass().getSimpleName(), aux)) {
                    command = commandMapper.fromString(Parser.getKeyCommandString(s));
                    commandResult = command.getKey().execute(Parser.getParameterMap(s, command.getValue()), repository);
                    header = Parser.getHeaderMap(req.getHeader("Accept").split("/")[0]);
                    list.add(new Pair<>((CommandViewHTML) viewEngine.getView(command.getKey().getClass().getSimpleName(), header.get("accept")), commandResult));
                }

                ret = list.size() > 1 ? CommandViewHTML.mergeHTMLs(list) : list.get(0).getKey().getStringToPrint(list.get(0).getValue());
            }
        } catch(CommandException e) {
            resp.setStatus(404);
            return;
        }

        OutputStream os = resp.getOutputStream();
        byte[] respBodyBytes = ret.getBytes(utf8);
        resp.setStatus(200);
        resp.setContentLength(respBodyBytes.length);
        os.write(respBodyBytes);
        os.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        _logger.info("{} on '{}' with accept:'{}', instance {}",
                req.getMethod(), req.getRequestURI(), req.getHeader("Accept"),
                this.hashCode());
        String aux = req.getMethod() + " " + req.getRequestURI();
        StringBuilder s = new StringBuilder();
        new BufferedReader(new InputStreamReader(req.getInputStream())).lines().forEach(s::append);
        aux += " " + s.toString();

        Charset utf8 = Charset.forName("utf-8");
        resp.setContentType(String.format(req.getHeader("Accept"), utf8.name()));

        CommandResult commandResult;
        try {
            Pair<Command, Function<String, Map<String, Object>>> command = commandMapper.fromString(Parser.getKeyCommandString(aux));
            commandResult = command.getKey().execute(Parser.getParameterMap(aux, command.getValue()), repository);
        } catch(CommandException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.setStatus(303);
        resp.setHeader("Location", req.getRequestURI() + "/" + commandResult.getValue());
    }
}
