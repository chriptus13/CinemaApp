package pt.isel.ls.model;

import java.util.HashMap;
import java.util.Map;

public class HeaderParser {
    public static Map<String, String> getHeaders(String str) {
        Map<String, String> res = new HashMap<>();
        String[] pairs = str.split("\\|");
        for (String s : pairs) {
            String[] values = s.split(":");
            res.put(values[0], values[1]);
        }
        return res;
    }
}
