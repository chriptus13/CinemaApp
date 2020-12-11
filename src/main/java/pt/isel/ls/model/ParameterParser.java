package pt.isel.ls.model;

import pt.isel.ls.model.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParameterParser {

    /**
     * Gets the list of parameters in the specified string
     *
     * @param str Input string
     * @param f   Function that extracts the parameters from the pathname
     * @return Map with all the parameters
     */
    public static Map<String, Object> getParameters(String str, Map<String, Object> f) {
        Map<String, Object> parameters = new HashMap<>(f);

        for (String s : str.split("&")) {
            String[] aux = s.split("=");
            if (aux.length == 2) {
                Object val = Utils.isInteger(aux[1]) ? Integer.parseInt(aux[1]) : aux[1].replace('+', ' ');

                if (parameters.computeIfPresent(aux[0], (key, value) -> {
                    ArrayList<Object> arr = value instanceof ArrayList ? (ArrayList<Object>) value : new ArrayList<>();
                    if (!(value instanceof ArrayList)) arr.add(value);
                    arr.add(val);
                    return arr;
                }) == null) parameters.put(aux[0], val);
            }
        }
        return parameters;
    }
}
