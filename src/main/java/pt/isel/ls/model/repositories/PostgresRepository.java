package pt.isel.ls.model.repositories;

import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.model.exceptions.*;

import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class PostgresRepository extends SQLRepository {
    /**
     * Gets a connection to the POSTGRES Server
     *
     * @return Connection to the POSTGRES Server
     * @throws SQLException if any error occurs during the Connection
     */
    protected Connection getConnection() throws SQLException {
        //Use Environment variable
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);

    }

    @Override
    public Collection<Session> getSessionsInCinemaToday(int cID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM CINEMA WHERE cID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentCinemaException();
            Cinema c = new Cinema(cID, rs.getString(2), rs.getString(3));

            // Getting current date

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ? AND date::date = now()::date";
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
    public Collection<Session> getSessionsInTheaterToday(int cID, int tID) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM THEATER AS T INNER JOIN CINEMA AS C ON C.cID = T.cID WHERE T.cID=? AND T.tID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, tID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentTheaterException();
            Cinema c = new Cinema(cID, rs.getString(8), rs.getString(9));
            Theater t = new Theater(tID, c, rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));

            // Getting current date
            sql = "SELECT * FROM SESSIONS AS S INNER JOIN MOVIE AS M ON S.mID=M.mID " +
                    "WHERE cID=? AND tID=? AND date::date = now()::date";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, tID);
            rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Movie m = new Movie(rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
                Session s = new Session(rs.getInt(4), t, m, new java.sql.Date(rs.getTimestamp(5).getTime()));
                res.add(s);
            }
            return res;
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }
    }

    @Override
    public Collection<Session> getSessionsAtDate(int cID, Date date) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM CINEMA WHERE cID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentCinemaException();
            Cinema c = new Cinema(cID, rs.getString(2), rs.getString(3));

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ? AND date::date = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setDate(2, new java.sql.Date(date.getTime()));
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
    public Collection<Session> getSessionsWithMovieAtDateInCity(int mID, Date date, String city) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "WHERE S.mID = ? AND date::date = ? AND city = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setDate(2, new java.sql.Date(date.getTime()));
            p.setString(3, city);
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

    @Override
    public Collection<Session> getSessionsWithMovieAtDateInCinema(int mID, Date date, int cID) throws CommandException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "WHERE S.mID = ? date::date = ? AND S.cID = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setDate(2, new java.sql.Date(date.getTime()));
            p.setInt(3, cID);
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

    @Override
    public Collection<Session> getSessionsWithMovieAtDateWithNAvailableSeats(int mID, Date date, int minAvailableSeats) throws CommandException {
        LinkedList<Session> sessions = new LinkedList<>();
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID " +
                    "INNER JOIN CINEMA AS C ON C.cID=S.cID " +
                    "WHERE S.mID = ? AND date::date = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new java.sql.Date(rs.getTimestamp(5).getTime()));
                sessions.add(s);
            }
        } catch(SQLException e) {
            throw new DatabaseErrorException();
        }

        LinkedList<Session> res = new LinkedList<>(sessions);
        for(Session s : sessions) {
            int available = getNAvailableTickets(s);
            if(available < minAvailableSeats)
                res.remove(s);
        }
        return res;
    }
}
