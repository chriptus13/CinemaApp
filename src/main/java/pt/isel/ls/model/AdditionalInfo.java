package pt.isel.ls.model;

import java.util.LinkedList;
import java.util.List;

public class AdditionalInfo {
    public static List<String> translator(String aux, String toTranslate) {
        LinkedList<String> ret = new LinkedList<>();
        ret.add(toTranslate); // Added by andre: tests
        switch (aux) {
            case "GetSessionCommand":
                ret.add(toTranslate + "/tickets");
                break;
            case "GetCinemaCommand":
                ret.add(toTranslate + "/theaters");
                ret.add(toTranslate + "/sessions");
                break;
            case "GetTheaterCommand":
                ret.add(toTranslate + "/sessions");
                break;
            case "GetMovieCommand":
                ret.add(toTranslate + "/sessions");
                break;
        }
        return ret;
    }
}
