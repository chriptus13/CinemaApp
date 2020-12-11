package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class Utils {
    private static final String MSSQL_USER = "SA", MSSQL_PASSWORD = "!Si12017", MSSQL_HOST = "localhost", MSSQL_DATABASE = "CinemaDB";
    private static final int MSSQL_PORT = 1433;

    public static Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(MSSQL_USER);
        ds.setPassword(MSSQL_PASSWORD);
        ds.setServerName(MSSQL_HOST);
        ds.setPortNumber(MSSQL_PORT);
        ds.setDatabaseName(MSSQL_DATABASE);
        return ds.getConnection();
    }
}
