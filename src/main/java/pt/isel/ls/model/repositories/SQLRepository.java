package pt.isel.ls.model.repositories;

import pt.isel.ls.model.MovieAPI;
import pt.isel.ls.model.Repository;
import pt.isel.ls.model.entities.*;
import pt.isel.ls.model.exceptions.*;

import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Base class for SQL repositories to reduce code duplication
 */
public abstract class SQLRepository implements Repository {
    protected abstract Connection getConnection() throws SQLException;

    @Override
    public int insertMovie(Movie movie) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM MOVIE WHERE mid=?");
            p.setInt(1, movie.getId());
            if(p.executeQuery().next()) throw new ExistentMovieException();
            p = con.prepareStatement("INSERT INTO MOVIE (mID,title,relYear,duration) VALUES(?,?,?,?)");
            p.setInt(1, movie.getId());
            p.setString(2, "");
            p.setInt(3, -1);
            p.setInt(4, -1);
            p.executeUpdate();
            return movie.getId();
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Movie> getMovies() throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM MOVIE");
            ResultSet rs = p.executeQuery();
            LinkedList<Movie> res = new LinkedList<>();
            while(rs.next()) {
                Movie movie;
                int mid = rs.getInt(1);
                if(rs.getInt(3) == -1) {
                    movie = MovieAPI.getMovieFromMovieDB(mid);
                    if(movie == null) {
                        p = con.prepareStatement("DELETE FROM MOVIE WHERE mID = ?");
                        p.setInt(1, mid);
                        p.executeUpdate();
                        continue;
                    }
                    p = con.prepareStatement("UPDATE MOVIE SET title = ?, relYear = ?, duration = ? WHERE mID = ?");
                    p.setString(1, movie.getName());
                    p.setInt(2, movie.getReleaseYear());
                    p.setInt(3, movie.getDuration());
                    p.setInt(4, mid);
                    p.executeUpdate();
                } else movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                res.add(movie);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Movie getMovie(int mID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM MOVIE WHERE mID = ?");
            p.setInt(1, mID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentMovieException();
            if(rs.getInt(3) == -1) {
                int mid = rs.getInt(1);
                Movie movie = MovieAPI.getMovieFromMovieDB(mid);
                if(movie == null) {
                    p = con.prepareStatement("DELETE FROM MOVIE WHERE mID = ?");
                    p.setInt(1, mid);
                    p.executeUpdate();
                    throw new NonExistentMovieException();
                }
                p = con.prepareStatement("UPDATE MOVIE SET title = ?, relYear = ?, duration = ? WHERE mID = ?");
                p.setString(1, movie.getName());
                p.setInt(2, movie.getReleaseYear());
                p.setInt(3, movie.getDuration());
                p.setInt(4, movie.getId());
                p.executeUpdate();
                return movie;
            }
            return new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public int insertCinema(Cinema cinema) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM CINEMA WHERE name = ? AND city = ?");
            p.setString(1, cinema.getName());
            p.setString(2, cinema.getCity());
            if(p.executeQuery().next()) throw new ExistentCinemaException();

            p = con.prepareStatement("INSERT INTO CINEMA(name,city) VALUES(?,?)");
            p.setString(1, cinema.getName());
            p.setString(2, cinema.getCity());
            p.executeUpdate();

            p = con.prepareStatement("SELECT cID FROM CINEMA WHERE name = ? AND city = ?");
            p.setString(1, cinema.getName());
            p.setString(2, cinema.getCity());
            ResultSet rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Cinema getCinema(int cID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM CINEMA WHERE cID = ?");
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentCinemaException();
            return new Cinema(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Cinema> getCinemas() throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM CINEMA");
            ResultSet rs = p.executeQuery();
            LinkedList<Cinema> res = new LinkedList<>();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(1), rs.getString(2), rs.getString(3));
                res.add(c);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public int insertTheater(Theater t) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM THEATER WHERE cID = ? AND name = ? AND nSeatsPerRow = ? AND nRows = ? AND availableSeats = ?");
            p.setInt(1, t.getCinema().getId());
            p.setString(2, t.getName());
            p.setInt(3, t.getNSeatsPerRow());
            p.setInt(4, t.getNRows());
            p.setInt(5, t.getAvailableSeats());
            if(p.executeQuery().next()) throw new ExistentTheaterException();

            p = con.prepareStatement("INSERT INTO THEATER(cid,name,nRows,nSeatsPerRow,availableSeats) VALUES(?,?,?,?,?)");
            p.setInt(1, t.getCinema().getId());
            p.setString(2, t.getName());
            p.setInt(3, t.getNRows());
            p.setInt(4, t.getNSeatsPerRow());
            p.setInt(5, t.getAvailableSeats());
            p.executeUpdate();

            p = con.prepareStatement("SELECT tID FROM THEATER WHERE cID = ? AND name = ? AND nSeatsPerRow = ? AND nRows = ? AND availableSeats = ?");
            p.setInt(1, t.getCinema().getId());
            p.setString(2, t.getName());
            p.setInt(3, t.getNSeatsPerRow());
            p.setInt(4, t.getNRows());
            p.setInt(5, t.getAvailableSeats());
            ResultSet rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch(SQLException e) {
            if(e.getMessage().contains("constraint \"TheaterForeign\"")) throw new NonExistentCinemaException();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Theater> getTheaters(int cID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM THEATER INNER JOIN CINEMA ON CINEMA.cID = THEATER.cID WHERE CINEMA.cID = ?");
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            LinkedList<Theater> res = new LinkedList<>();
            while(rs.next()) {
                Theater t = new Theater(rs.getInt(1), new Cinema(rs.getInt(2), rs.getString(8), rs.getString(9)),
                        rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                res.add(t);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Theater getTheater(int cID, int tID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            PreparedStatement p = con.prepareStatement("SELECT * FROM THEATER AS T INNER JOIN CINEMA AS C ON C.cID=T.cID WHERE T.cID = ? AND T.tID = ?");
            p.setInt(1, cID);
            p.setInt(2, tID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentTheaterException();
            return new Theater(rs.getInt(1), new Cinema(rs.getInt(2), rs.getString(8), rs.getString(9)), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public int insertSession(Session s) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            Timestamp dateBuffer = new Timestamp(s.getDate().getTime());
            int cID = s.getTheater().getCinema().getId(),
                    tID = s.getTheater().getId(), mID = s.getMovie().getId();

            // Verify if there isn't a session on the same theater already
            PreparedStatement p = con.prepareStatement("SELECT * FROM SESSIONS WHERE cID = ? AND tID = ? AND mID = ? AND date = ?");
            p.setInt(1, cID);
            p.setInt(2, tID);
            p.setInt(3, mID);
            p.setTimestamp(4, dateBuffer);
            if(p.executeQuery().next()) throw new ExistentSessionException();


            // Check if there's any sessions before in date conflict with new one
            p = con.prepareStatement("SELECT MAX(DATE) FROM SESSIONS WHERE DATE < ? AND cID = ? AND tID = ? ");
            p.setTimestamp(1, dateBuffer);
            p.setInt(2, cID);
            p.setInt(3, tID);
            ResultSet rs = p.executeQuery();
            if(rs.next() && rs.getDate(1) != null) {
                p = con.prepareStatement("SELECT duration FROM MOVIE WHERE mID = (SELECT mID FROM SESSIONS WHERE DATE = ?  AND cID = ? AND tID = ?) ");
                Timestamp test = rs.getTimestamp(1);
                p.setTimestamp(1, test);
                p.setInt(2, cID);
                p.setInt(3, tID);
                rs = p.executeQuery();
                if(rs.next() && test != null && (test.getTime() + (rs.getInt(1) * 60000)) > dateBuffer.getTime()) //60000=60*1000(mins to secs then secs to milis)
                    throw new SessionTimeCollisionException();
            }

            // Check if there's any sessions after in date conflict with new one
            p = con.prepareStatement("SELECT MIN(DATE) FROM SESSIONS WHERE DATE > ? AND cID = ? AND tID = ? ");
            p.setTimestamp(1, dateBuffer);
            p.setInt(2, cID);
            p.setInt(3, tID);
            rs = p.executeQuery();
            if(rs.next() && rs.getDate(1) != null) {
                p = con.prepareStatement("SELECT duration FROM MOVIE WHERE mID = ?");
                Date test = rs.getDate(1);
                p.setInt(1, mID);
                rs = p.executeQuery();
                if(rs.next() && test != null && dateBuffer.getTime() + (rs.getInt(1) * 60000) > test.getTime()) //60000=60*1000(mins to secs then secs to milis)
                    throw new SessionTimeCollisionException();
            }

            // Insert
            p = con.prepareStatement("INSERT INTO SESSIONS(cid,tid,mid,date) VALUES(?,?,?,?)");
            p.setInt(1, cID);
            p.setInt(2, tID);
            p.setInt(3, mID);
            p.setTimestamp(4, dateBuffer);
            p.executeUpdate();

            p = con.prepareStatement("SELECT sID FROM SESSIONS WHERE cID = ? AND tID = ? AND mID = ? AND DATE = ?");
            p.setInt(1, cID);
            p.setInt(2, tID);
            p.setInt(3, mID);
            p.setTimestamp(4, dateBuffer);
            rs = p.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch(SQLException e) {
            if(e.getMessage().contains("constraint \"SessionsTheaterForeign\""))
                throw new NonExistentTheaterException();
            else if(e.getMessage().contains("constraint \"SessionsMovieForeign\""))
                throw new NonExistentMovieException();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Session> getSessionsInTheater(int cID, int tID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM THEATER AS T INNER JOIN CINEMA AS C ON C.cID = T.cID WHERE T.cID=? AND T.tID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, tID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentTheaterException();
            Cinema c = new Cinema(cID, rs.getString(8), rs.getString(9));
            Theater t = new Theater(tID, c, rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN MOVIE AS M ON S.mID=M.mID WHERE cID=? AND tID=?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, tID);
            rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Movie m = new Movie(rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
                res.add(s);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Session> getSessionsInCinema(int cID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM CINEMA WHERE cID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentCinemaException();
            Cinema c = new Cinema(cID, rs.getString(2), rs.getString(3));

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new java.sql.Date(rs.getTimestamp(5).getTime()));
                res.add(s);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Session getSession(int cID, int sID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN CINEMA AS C ON S.cID=C.cID " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE (S.sID = ? AND S.cID = ?)";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, sID);
            p.setInt(2, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentSessionException();
            Cinema c = new Cinema(cID, rs.getString(13), rs.getString(14));
            Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
            Movie m = new Movie(rs.getInt(15), rs.getString(16), rs.getInt(17), rs.getInt(18));

            return new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void insertTicket(Ticket t) throws CommandExecutionException {
        try(Connection con = getConnection()) {

            PreparedStatement p = con.prepareStatement("SELECT * FROM TICKETS WHERE cID = ? AND tID = ? AND sID = ? AND tkID = ?");
            p.setInt(1, t.getSession().getTheater().getCinema().getId());
            p.setInt(2, t.getSession().getTheater().getId());
            p.setInt(3, t.getSession().getId());
            p.setString(4, t.getID());
            if(p.executeQuery().next()) throw new ExistentTicketException();

            p = con.prepareStatement("SELECT * FROM THEATER WHERE cID = ? AND tID = ?");
            p.setInt(1, t.getSession().getTheater().getCinema().getId());
            p.setInt(2, t.getSession().getTheater().getId());
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentTheaterException();
            int maxSeat = rs.getInt(5);
            int maxRow = rs.getInt(4);
            if(t.getRowInteger() > maxRow) throw new InvalidRowException();
            if(t.getSeatInteger() > maxSeat) throw new InvalidSeatException();

            // Insert
            p = con.prepareStatement("INSERT INTO TICKETS VALUES(?,?,?,?)");
            p.setInt(1, t.getSession().getTheater().getCinema().getId());
            p.setInt(2, t.getSession().getTheater().getId());
            p.setInt(3, t.getSession().getId());
            p.setString(4, t.getID());
            p.executeUpdate();

        } catch(SQLException e) {
            if(e.getMessage().contains("constraint \"TicketsSessionForeign\""))
                throw new NonExistentSessionException();
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Ticket> getTicketsInSession(Session s) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ? AND S.tID = ? AND S.sID = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, s.getTheater().getCinema().getId());
            p.setInt(2, s.getTheater().getId());
            p.setInt(3, s.getId());
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentSessionException();
            Cinema c = new Cinema(rs.getInt(12), rs.getString(13), rs.getString(14));
            Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
            Movie m = new Movie(rs.getInt(15), rs.getString(16), rs.getInt(17), rs.getInt(18));
            Session session = new Session(s.getId(), t, m, new java.sql.Date(rs.getTimestamp(5).getTime()));

            sql = "SELECT * FROM TICKETS WHERE cID = ? AND tID = ? AND sID = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, s.getTheater().getCinema().getId());
            p.setInt(2, s.getTheater().getId());
            p.setInt(3, s.getId());
            rs = p.executeQuery();
            LinkedList<Ticket> res = new LinkedList<>();
            while(rs.next()) {
                Ticket ticket = new Ticket(rs.getString(4), session);
                res.add(ticket);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Ticket getTicket(Session s, String tkID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM TICKETS AS TK INNER JOIN SESSIONS AS S ON (TK.sID=S.sID AND TK.tID=S.tID AND TK.cID=S.cID) " +
                    "INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE TK.cID = ? AND TK.tID = ? AND TK.sID = ? AND TK.tkID = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, s.getTheater().getCinema().getId());
            p.setInt(2, s.getTheater().getId());
            p.setInt(3, s.getId());
            p.setString(4, tkID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentTicketException();
            Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
            Theater t = new Theater(rs.getInt(10), c, rs.getString(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));
            Movie m = new Movie(rs.getInt(19), rs.getString(20), rs.getInt(21), rs.getInt(22));
            Session session = new Session(s.getId(), t, m, new java.sql.Date(rs.getTimestamp(9).getTime()));
            return new Ticket(tkID, session);
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public int getNAvailableTickets(Session s) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql;
            PreparedStatement p;
            ResultSet rs;
            int availableSeats;
            if(s.getTheater().getName().isEmpty()) {
                sql = "SELECT availableSeats FROM THEATER WHERE cID=? AND tID=?";

                p = con.prepareStatement(sql);
                p.setInt(1, s.getTheater().getCinema().getId());
                p.setInt(2, s.getTheater().getId());
                rs = p.executeQuery();
                if(!rs.next()) throw new NonExistentTheaterException();

                availableSeats = rs.getInt(1);
                sql = "SELECT * FROM SESSIONS WHERE cID=? AND tID=? AND sID=?";
                p = con.prepareStatement(sql);
                p.setInt(1, s.getTheater().getCinema().getId());
                p.setInt(2, s.getTheater().getId());
                p.setInt(3, s.getId());
                if(!p.executeQuery().next()) throw new NonExistentSessionException();
            } else availableSeats = s.getTheater().getAvailableSeats();
            sql = "SELECT COUNT(*) FROM TICKETS WHERE cID=? AND tID=? AND sID=?";
            p = con.prepareStatement(sql);
            p.setInt(1, s.getTheater().getCinema().getId());
            p.setInt(2, s.getTheater().getId());
            p.setInt(3, s.getId());
            rs = p.executeQuery();
            if(!rs.next()) return availableSeats;
            int nTickets = rs.getInt(1);
            return availableSeats - nTickets;

        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public void deleteTicket(Session s, List<String> tickets) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "DELETE FROM TICKETS WHERE cID=? AND tID=? AND sID=? AND tkID=?";
            PreparedStatement p;
            for(String tkID : tickets) {
                p = con.prepareStatement(sql);
                p.setInt(1, s.getTheater().getCinema().getId());
                p.setInt(2, s.getTheater().getId());
                p.setInt(3, s.getId());
                p.setString(4, tkID);
                p.executeUpdate();
            }
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Session> getSessionsWithMovie(Integer mID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "WHERE S.mID = ? ";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            ResultSet rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new java.sql.Date(rs.getTimestamp(5).getTime()));
                res.add(s);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }
}
