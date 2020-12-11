package pt.isel.ls.model.entities;

public class Ticket {
    public final String id;
    public final Session session;


    public Ticket(Session session, char row, int seat) {
        this.session = session;
        id = row + "" + seat;
    }

    public Ticket(String id, Session session) {
        this.id = id;
        this.session = session;
    }

    public int getRowInteger() {
        return id.toLowerCase().charAt(0) - 'a' + 1;
    }

    public int getSeatInteger() {
        return Integer.parseInt(id.substring(1, id.length()));
    }

    public Session getSession() {
        return session;
    }

    public String getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ticket && session.equals(((Ticket) obj).session) && id.equals(((Ticket) obj).id);
    }

    @Override
    public String toString() {
        return "Ticket: " + id;
    }

    public static boolean isTkID(String tkID) {
        try {
            if (tkID == null) return false;
            String aux = tkID.substring(0, 1);
            if (aux.charAt(0) < 'A' || aux.charAt(0) > 'Z') return false;
            aux = tkID.substring(1);
            Integer.parseInt(aux);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
