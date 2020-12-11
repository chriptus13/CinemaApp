package pt.isel.ls.view.html;

import pt.isel.ls.model.CommandResult;
import pt.isel.ls.model.entities.Ticket;
import pt.isel.ls.model.utils.Pair;

import java.util.Collection;
import java.util.HashMap;

public class GetTicketsInSessionCommandHTMLView extends CommandViewHTML {
    private static final String TITLE = "Tickets";
    private static final String CSS_STYLES = "table {\n" +
            "font-family: arial, sans-serif;\n" +
            "}\n" +
            "td ,th{\n" +
            "border: 1px solid #000000;\n" +
            "}\n" +
            "tr:nth-child(1){\n" +
            "background-color: #CCCCCC;\n" +
            "}\n" +
            ".centered {\n" +
            "text-align: center;\n" +
            "}\n";
    private static final String DEFAULT_CONTENT = "<h1>Tickets</h1>\n" +
            "<p>No Tickets were found!</p>\n";

    @Override
    public String getTitle(CommandResult commandResult) {
        return TITLE;
    }

    @Override
    public String getCSSStyles() {
        return CSS_STYLES;
    }

    @Override
    public String getContent(CommandResult commandResult) {
        Collection<Ticket> tickets = (Collection<Ticket>) commandResult.getValue();
        if(tickets.size() == 0) return DEFAULT_CONTENT;
        StringBuilder result = new StringBuilder();
        result.append("<div><h1 class='centered'>Tickets</h1>\n" +
                "</div>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>Rows</th>\n" +
                "</tr>\n");
        HashMap<Pair<Integer, Integer>, Ticket> filler = new HashMap();
        Ticket buff = null;
        for(Ticket t : tickets) {
            if(buff == null) buff = t;
            filler.put(new Pair<>(t.getRowInteger(), t.getSeatInteger()), t);
        }

        for(int row = 1; row <= buff.getSession().getTheater().getNRows(); ++row) {
            result.append("<tr>\n <th> " + (char) ('A' + row - 1) + " </th>\n");
            for(int seat = 1; seat <= buff.getSession().getTheater().getNSeatsPerRow(); seat++) {
                Ticket test = filler.get(new Pair<>(row, seat));
                if(test != null)
                    result.append("<td bgcolor=\"#ff1a1a\"> <a href=\"/cinemas/" + buff.getSession().getTheater().getCinema().getId() + "/theaters/" + buff.getSession().getTheater().getId() + "/sessions/" + buff.getSession().getId() + "/tickets/" + test.getID() + "\"> " + seat + " </a> </td>\n");//color red the entry
                else result.append("<td>" + seat + "</td>\n");//don't color the entry
            }
            result.append("<tr>\n");
        }
        result.append("</table>\n");
        return result.toString();
    }

    @Override
    public String getBackButton(CommandResult commandResult) {
        return "";
    }
}
