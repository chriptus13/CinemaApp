package pt.isel.ls.model.commands.repositories;

import pt.isel.ls.model.Repository;
import pt.isel.ls.model.entities.*;
import pt.isel.ls.model.exceptions.CommandExecutionException;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestRepository implements Repository {

    @Override
    public int insertMovie(Movie movie) {
        return -1;
    }

    @Override
    public Collection<Movie> getMovies() {
        return Collections.singleton(new Movie(0, "Test", 2000, 1));
    }

    @Override
    public Movie getMovie(int mID) {
        return new Movie(0, "Test", 2000, 1);
    }

    @Override
    public int insertCinema(Cinema cinema) {
        return -1;
    }

    @Override
    public Collection<Cinema> getCinemas() {
        return Collections.singleton(new Cinema(0, "Test", "Test"));
    }

    @Override
    public Cinema getCinema(int cID) {
        return new Cinema(0, "Test", "Test");
    }

    @Override
    public int insertTheater(Theater theater) {
        return -1;
    }

    @Override
    public Collection<Theater> getTheaters(int cID) {
        return Collections.singleton(new Theater(0, 0, "Test", 0, 0, 0));
    }

    @Override
    public Theater getTheater(int cID, int tID) {
        return new Theater(0, 0, "Test", 0, 0, 0);
    }

    @Override
    public int insertSession(Session session) {
        return -1;
    }

    @Override
    public Collection<Session> getSessionsInTheater(int cID, int tID) {
        return Collections.singleton(new Session(cID, tID, 0, null));
    }

    @Override
    public Collection<Session> getSessionsInCinema(int cID) {
        return Collections.singleton(new Session(cID, 0, 0, null));
    }

    @Override
    public Session getSession(int cID, int sID) {
        return new Session(cID, -1, sID);
    }

    @Override
    public Collection<Session> getSessionsInCinemaToday(int cID) {
        return Collections.singleton(new Session(cID, 0, 0, null));
    }

    @Override
    public Collection<Session> getSessionsInTheaterToday(int cID, int tID) {
        return Collections.singleton(new Session(cID, tID, 0, null));
    }

    @Override
    public Collection<Session> getSessionsAtDate(int cID, Date date) {
        return Collections.singleton(new Session(cID, 0, 0, date));
    }

    @Override
    public Collection<Session> getSessionsWithMovieAtDateInCity(int mID, Date date, String city) {
        return Collections.singleton(new Session(-1, null, null, date));
    }

    @Override
    public Collection<Session> getSessionsWithMovieAtDateInCinema(int mID, Date date, int cID) {
        return Collections.singleton(new Session(-1, null, null, date));
    }

    @Override
    public Collection<Session> getSessionsWithMovieAtDateWithNAvailableSeats(int mID, Date date, int availableSeats) {
        return Collections.singleton(new Session(-1, null, null, date));
    }

    @Override
    public void insertTicket(Ticket t) {

    }

    @Override
    public Collection<Ticket> getTicketsInSession(Session s) {
        return Collections.singleton(new Ticket(s, '0', -1));
    }

    @Override
    public Ticket getTicket(Session s, String tkID) {
        return new Ticket(s, '0', -1);
    }

    @Override
    public int getNAvailableTickets(Session s) {
        return 0;
    }

    @Override
    public void deleteTicket(Session s, List<String> tkID) {

    }

    @Override
    public Collection<Session> getSessionsWithMovie(Integer mID) throws CommandExecutionException {
        return null;
    }
}
