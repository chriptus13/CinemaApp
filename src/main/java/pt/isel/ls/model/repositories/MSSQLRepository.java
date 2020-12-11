package pt.isel.ls.model.repositories;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.model.entities.Cinema;
import pt.isel.ls.model.entities.Movie;
import pt.isel.ls.model.entities.Session;
import pt.isel.ls.model.entities.Theater;
import pt.isel.ls.model.exceptions.*;

import java.sql.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class MSSQLRepository extends SQLRepository {
    private static final String MSSQL_USER = "SA", MSSQL_PASSWORD = "<YourStrong!Passw0rd>", MSSQL_HOST = "localhost", MSSQL_DATABASE = "Cinema";
    private static final int MSSQL_PORT = 1401;

    /**
     * Gets a connection to the MSSQL Server
     *
     * @return Connection to the MSSQL Server
     * @throws SQLServerException if any error occurs during the Connection
     */
    protected Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(MSSQL_USER);
        ds.setPassword(MSSQL_PASSWORD);
        ds.setServerName(MSSQL_HOST);
        ds.setPortNumber(MSSQL_PORT);
        ds.setDatabaseName(MSSQL_DATABASE);
        return ds.getConnection();
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
            p = con.prepareStatement("SELECT CONVERT (date, SYSDATETIME()),CONVERT (date, CURRENT_TIMESTAMP),CONVERT (date, GETDATE())");
            rs = p.executeQuery();
            rs.next();
            Date currDate = rs.getDate(1);
            sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, new Timestamp(currDate.getTime()).toLocalDateTime().getYear());
            p.setInt(3, new Timestamp(currDate.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(4, new Timestamp(currDate.getTime()).toLocalDateTime().getDayOfMonth());
            rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
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
            p = con.prepareStatement("SELECT CONVERT (date, SYSDATETIME()),CONVERT (date, CURRENT_TIMESTAMP),CONVERT (date, GETDATE())");
            rs = p.executeQuery();
            rs.next();
            Date currDate = rs.getDate(1);

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN MOVIE AS M ON S.mID=M.mID " +
                    "WHERE cID=? AND tID=? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, tID);
            p.setInt(3, new Timestamp(currDate.getTime()).toLocalDateTime().getYear());
            p.setInt(4, new Timestamp(currDate.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(5, new Timestamp(currDate.getTime()).toLocalDateTime().getDayOfMonth());
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
    public Collection<Session> getSessionsAtDate(int cID, Date date) throws CommandExecutionException {
        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM CINEMA WHERE cID=?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, cID);
            ResultSet rs = p.executeQuery();
            if(!rs.next()) throw new NonExistentCinemaException();
            Cinema c = new Cinema(cID, rs.getString(2), rs.getString(3));

            sql = "SELECT * FROM SESSIONS AS S INNER JOIN THEATER AS T ON (T.tID=S.tID AND T.cID=S.cID) " +
                    "INNER JOIN MOVIE AS M ON M.mID=S.mID WHERE S.cID = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
            p = con.prepareStatement(sql);
            p.setInt(1, cID);
            p.setInt(2, new Timestamp(date.getTime()).toLocalDateTime().getYear());
            p.setInt(3, new Timestamp(date.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(4, new Timestamp(date.getTime()).toLocalDateTime().getDayOfMonth());
            rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
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
                    "WHERE S.mID = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ? AND city = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setInt(2, new Timestamp(date.getTime()).toLocalDateTime().getYear());
            p.setInt(3, new Timestamp(date.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(4, new Timestamp(date.getTime()).toLocalDateTime().getDayOfMonth());
            p.setString(5, city);
            ResultSet rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
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
                    "WHERE S.mID = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ? AND S.cID = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setInt(2, new Timestamp(date.getTime()).toLocalDateTime().getYear());
            p.setInt(3, new Timestamp(date.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(4, new Timestamp(date.getTime()).toLocalDateTime().getDayOfMonth());
            p.setInt(5, cID);
            ResultSet rs = p.executeQuery();
            LinkedList<Session> res = new LinkedList<>();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
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
                    "WHERE S.mID = ? AND YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
            PreparedStatement p = con.prepareStatement(sql);
            p.setInt(1, mID);
            p.setInt(2, new Timestamp(date.getTime()).toLocalDateTime().getYear());
            p.setInt(3, new Timestamp(date.getTime()).toLocalDateTime().getMonth().getValue());
            p.setInt(4, new Timestamp(date.getTime()).toLocalDateTime().getDayOfMonth());
            ResultSet rs = p.executeQuery();
            while(rs.next()) {
                Cinema c = new Cinema(rs.getInt(16), rs.getString(17), rs.getString(18));
                Theater t = new Theater(rs.getInt(6), c, rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                Movie m = new Movie(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getInt(15));
                Session s = new Session(rs.getInt(4), t, m, new Date(rs.getTimestamp(5).getTime()));
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
