package pt.isel.ls.model;

import pt.isel.ls.model.entities.*;
import pt.isel.ls.model.exceptions.CommandException;
import pt.isel.ls.model.exceptions.CommandExecutionException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Specifies the operations for the repositories which hold the data
 */
public interface Repository {

    // Movie management operations:

    /**
     * Inserts a Movie passed as a parameter in the repository
     *
     * @param movie Movie to be inserted
     * @throws CommandExecutionException if there's anything preventing the insertion
     */
    int insertMovie(Movie movie) throws CommandExecutionException;

    /**
     * Gets a container with all the Movies in the repository
     *
     * @return Collection with all the Movies
     * @throws CommandExecutionException if there's anything preventing the operation
     */
    Collection<Movie> getMovies() throws CommandExecutionException;

    /**
     * Gets the Movie with the specified ID
     *
     * @param mID Movie ID
     * @return Movie with the specified ID
     * @throws CommandExecutionException if the Movie doesn't exist in the database
     */
    Movie getMovie(int mID) throws CommandExecutionException;

    // Cinema management operations:

    /**
     * Inserts a Cinema passed as a parameter in the repository
     *
     * @param cinema Cinema to be inserted
     * @throws CommandExecutionException if there's anything preventing the insertion
     */
    int insertCinema(Cinema cinema) throws CommandExecutionException;

    /**
     * Gets a container with all the Cinemas in the repository
     *
     * @return Collection with all the Cinemas
     * @throws CommandExecutionException if there's anything preventing the operation
     */
    Collection<Cinema> getCinemas() throws CommandExecutionException;

    /**
     * Gets the Cinema with the specified ID
     *
     * @param cID Cinema ID
     * @return Cinema with the specified ID
     * @throws CommandExecutionException if the Cinema doesn't exist in the repository
     */
    Cinema getCinema(int cID) throws CommandExecutionException;

    /**
     * Inserts a Theater passed as a parameter in the repository
     *
     * @param theater Theater to be inserted
     * @throws CommandExecutionException if there's anything preventing the insertion
     */
    int insertTheater(Theater theater) throws CommandExecutionException;

    /**
     * Gets a container with all the Theaters for the specified Cinema ID in the database
     *
     * @param cID Cinema ID
     * @return Collection with all the Theaters for the specified Cinema ID
     * @throws CommandExecutionException if there's anything preventing the operation
     */
    Collection<Theater> getTheaters(int cID) throws CommandExecutionException;

    /**
     * Gets the Theater with the specified ID in the specified Cinema ID
     *
     * @param cID Cinema ID
     * @param tID Theater ID
     * @return Theater with the specified ID in the specified Cinema ID
     * @throws CommandExecutionException if the Theater doesn't exist in the database
     */
    Theater getTheater(int cID, int tID) throws CommandExecutionException;

    // Session management operations:

    /**
     * Inserts a Session passed as a parameter in the repository
     *
     * @param session Session to be inserted
     * @throws CommandExecutionException if there's anything preventing the insertion
     */
    int insertSession(Session session) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions for the Theater ID in a Cinema ID in the repository
     *
     * @param cID Cinema ID
     * @param tID Theater ID
     * @return Collection with all the Sessions for the Theater ID in a Cinema ID
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsInTheater(int cID, int tID) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions for a Cinema ID in the repository
     *
     * @param cID Cinema ID
     * @return Collection with all the Sessions for the Cinema ID
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsInCinema(int cID) throws CommandExecutionException;

    /**
     * Gets the Session with the specified ID in the specified Cinema ID
     *
     * @param cID Cinema ID
     * @param sID Session ID
     * @return Session with the specified ID in the specified Cinema ID
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Session getSession(int cID, int sID) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions in the Cinema ID for today in the repository
     *
     * @param cID Cinema ID
     * @return Collection with all the Sessions in the Cinema ID for today
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsInCinemaToday(int cID) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions in the Theater ID from the Cinema ID for today in the repository
     *
     * @param cID Cinema ID
     * @param tID Theater ID
     * @return Collection with all the Sessions in the Theater ID from the Cinema ID for today
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsInTheaterToday(int cID, int tID) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions in the Cinema ID in the date given
     *
     * @param cID  Cinema ID
     * @param date Date
     * @return Collection with all the Sessions in the Cinema ID for the date given
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsAtDate(int cID, Date date) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions with the Movie ID during date given at specified city in Cinema ID or with a certain available seats
     *
     * @param mID  Movie ID
     * @param date Date
     * @param city Optional name of the city
     * @return Collection with all the Sessions
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsWithMovieAtDateInCity(int mID, Date date, String city) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions with the Movie ID during date given in Cinema ID
     *
     * @param mID  Movie ID
     * @param date Date
     * @param cID  Optional cinema ID
     * @return Collection with all the Sessions
     * @throws CommandException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsWithMovieAtDateInCinema(int mID, Date date, int cID) throws CommandException;

    /**
     * Gets a container with all the Sessions with the Movie ID during date given in Cinema ID
     *
     * @param mID            Movie ID
     * @param date           Date
     * @param availableSeats Optional available seats in theater
     * @return Collection with all the Sessions
     * @throws CommandException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsWithMovieAtDateWithNAvailableSeats(int mID, Date date, int availableSeats) throws CommandException;

    // Ticket management operations:

    /**
     * Inserts a Ticket passed as parameter in repository
     *
     * @param t Ticket to insert
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    void insertTicket(Ticket t) throws CommandExecutionException;

    /**
     * Gets a container with all the Tickets in a session
     *
     * @param s Session to analyse
     * @return Collection with all the Tickets
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Ticket> getTicketsInSession(Session s) throws CommandExecutionException;

    /**
     * Gets a Ticket tkID in Session
     *
     * @param s    Session to analyse
     * @param tkID Ticket ID
     * @return Ticket in specified Session with specified id
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Ticket getTicket(Session s, String tkID) throws CommandExecutionException;

    /**
     * Gets the number of available tickets in a Session
     *
     * @param s Session to analyse
     * @return number of available tickets in a Session
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    int getNAvailableTickets(Session s) throws CommandExecutionException;

    /**
     * Deletes tickets in a session from the database
     *
     * @param s    Session to analyse
     * @param tkID Ticket ID
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    void deleteTicket(Session s, List<String> tkID) throws CommandExecutionException;

    /**
     * Gets a container with all the Sessions with the Movie ID
     * * @param mID Movie ID
     *
     * @return Collection with all the Sessions
     * @throws CommandExecutionException if there's is anything preventing the operation
     */
    Collection<Session> getSessionsWithMovie(Integer mID) throws CommandExecutionException;
}
