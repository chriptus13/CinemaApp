package pt.isel.ls.model.entities;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that represents a Session in the repository
 */
public class Session {
    public final int id;
    public final Theater theater;
    public final Movie movie;
    public final Date date;

    public Session(int sessionID, Theater theater, Movie movie, Date date) {
        this.theater = theater;
        this.movie = movie;
        this.date = date;
        id = sessionID;
    }

    public Session(int cinemaID, int theaterID, int movieID, Date date) {
        this(-1, new Theater(theaterID, cinemaID, null, 0, 0, 0), new Movie(movieID, null, 0, 0), date);
    }

    public Session(int cinemaID, int theaterID, int sessionID) {
        this(sessionID, new Theater(cinemaID, theaterID), null, null);
    }

    public Theater getTheater() {
        return theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Session && theater.equals(((Session) obj).theater) && movie.equals(((Session) obj).movie) && date.equals(((Session) obj).date);
    }

    @Override
    public String toString() {
        SimpleDateFormat ret = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return ret.format(date) + " -> " + theater.getCinema().getName() + ", Theater: " + theater.getName() + ", Movie: " + movie.getName() + " (" + movie.getDuration() + " minutes)";
    }
}
