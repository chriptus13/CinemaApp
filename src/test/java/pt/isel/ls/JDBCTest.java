package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JDBCTest {
    private static final String CURRENT_DATABASE = "TEST",
            SQLSERVER_USER = "SA",
            SQLSERVER_PASSWORD = "<YourStrong!Passw0rd>",
            SQLSERVER_HOST = "localhost";
    private static final int SQLSERVER_PORT = 1401;

    private static Connection getConnection(String dataBase) throws SQLException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(SQLSERVER_USER);
        ds.setPassword(SQLSERVER_PASSWORD);
        ds.setServerName(SQLSERVER_HOST);
        ds.setPortNumber(SQLSERVER_PORT);
        if (dataBase != null) ds.setDatabaseName(CURRENT_DATABASE);
        return ds.getConnection();
    }

    @BeforeClass
    public static void build() throws SQLException {
        Connection connection = getConnection(null);
        connection.prepareStatement("CREATE DATABASE TEST").executeUpdate();
        connection.prepareStatement("USE TEST CREATE TABLE STUDENT(Nome VARCHAR(255) NOT NULL, Numero INT NOT NULL, PRIMARY KEY(Numero))").executeUpdate();
        connection.close();
    }

    @Test
    public void insert() throws SQLException {
        Connection connection = getConnection(CURRENT_DATABASE);
        connection.prepareStatement("INSERT INTO STUDENT VALUES ('Samuel Sampaio', 44020)").executeUpdate();
        assertTrue(connection.prepareStatement("SELECT * FROM STUDENT WHERE Nome = 'Samuel Sampaio' AND Numero = 44020").executeQuery().next());
        connection.prepareStatement("DELETE FROM STUDENT WHERE Numero = 44020").executeUpdate();
        connection.close();
    }

    @Test
    public void select() throws SQLException {
        Connection connection = getConnection(CURRENT_DATABASE);
        connection.prepareStatement("INSERT INTO STUDENT VALUES ('Claúdio Bartolomeu', 43549)").executeUpdate();
        ResultSet rs = connection.prepareStatement("SELECT * FROM STUDENT WHERE Numero=43549").executeQuery();
        assertTrue(rs.next());
        assertEquals(43549, rs.getInt(2));
        connection.prepareStatement("DELETE FROM STUDENT WHERE Numero = 43549").executeUpdate();
        connection.close();
    }

    @Test
    public void update() throws SQLException {
        Connection connection = getConnection(CURRENT_DATABASE);
        connection.prepareStatement("INSERT INTO STUDENT VALUES ('André Martins', 43562)").executeUpdate();
        connection.prepareStatement("UPDATE STUDENT SET Numero = 404 WHERE Numero = 43562").executeUpdate();
        ResultSet rs = connection.prepareStatement("SELECT * FROM STUDENT WHERE Numero = 404").executeQuery();
        assertTrue(rs.next());
        assertEquals(404, rs.getInt(2));
        connection.prepareStatement("DELETE FROM STUDENT WHERE Numero = 404").executeUpdate();
        connection.close();
    }

    @Test
    public void delete() throws SQLException {
        Connection connection = getConnection(CURRENT_DATABASE);
        connection.prepareStatement("INSERT INTO STUDENT VALUES ('André Martins', 43562)").executeUpdate();
        ResultSet rs = connection.prepareStatement("SELECT * FROM STUDENT WHERE Nome = 'André Martins' AND Numero = 43562").executeQuery();
        assertTrue(rs.next());
        assertEquals(43562, rs.getInt(2));
        connection.prepareStatement("DELETE FROM STUDENT WHERE Numero = 43562").executeUpdate();
        assertFalse(connection.prepareStatement("SELECT * FROM STUDENT WHERE Nome = 'André Martins' AND Numero = 43562").executeQuery().next());
        connection.close();
    }

    @AfterClass
    public static void clear() throws SQLException {
        Connection connection = getConnection(null);
        connection.prepareStatement("DROP DATABASE " + CURRENT_DATABASE).execute();
        connection.close();
    }
}