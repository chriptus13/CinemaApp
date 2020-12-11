package pt.isel.ls.model;

import pt.isel.ls.model.exceptions.InvalidHeadersException;
import pt.isel.ls.model.exceptions.InvalidParametersException;
import pt.isel.ls.model.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Parser {

    public static String getKeyCommandString(String cmd) {
        String[] components = cmd.split(" ");
        if (components.length < 2) return "";
        return components[0].concat(" " + components[1]);
    }

    public static Map<String, Object> getParameterMap(String cmd, Function<String, Map<String, Object>> path) throws InvalidParametersException {
        String[] components = cmd.split(" ");
        if (components.length > 4) throw new InvalidParametersException();
        Map<String, Object> pars = path.apply(components[1]);
        for (int i = 2; i < components.length; i++)
            if (Utils.isParameter(components[i])) pars = ParameterParser.getParameters(components[i], pars);
        return pars;
    }

    public static Map<String, String> getHeaderMap(String cmd) throws InvalidHeadersException {
        String[] components = cmd.split(" ");
        for (int i = 2; i < components.length; i++)
            if (Utils.isHeader(components[i])) {
                Map<String, String> headers = HeaderParser.getHeaders(components[i]);
                if (headers.size() > 2 ||
                        headers.size() == 2 && !headers.containsKey("file-name") && !headers.containsKey("accept") ||
                        headers.size() == 1 && !(!headers.containsKey("file-name") || !headers.containsKey("accept")))
                    throw new InvalidHeadersException();
                return headers;
            }
        return new HashMap<>();
    }
}
