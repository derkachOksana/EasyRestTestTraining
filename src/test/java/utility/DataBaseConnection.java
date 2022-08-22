package utility;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public final class DataBaseConnection {

    private static DataBaseConnection dataBaseConnection;

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;
    private static Statement stmt;
    //private static Connection con;
    private  ResultSet rs;

    private DataBaseConnection() {
        URL = ConfProperties.getProperty("postgresql.url");
        USERNAME = ConfProperties.getProperty("postgresql.username");
        PASSWORD = ConfProperties.getProperty("postgresql.password");
    }

    public static DataBaseConnection getInstance() {
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }

    public void execute(String sqlScriptPath) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader(sqlScriptPath));
            sr.runScript(reader);
        } catch (SQLException | FileNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteAllOrdersFrom_order_associations () {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sqlQuery = "DELETE FROM order_associations";
            stmt = con.createStatement();
            stmt.execute(sqlQuery);
            stmt.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteAllOrdersFrom_orders () {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sqlQuery = "DELETE FROM orders";
            stmt = con.createStatement();
            stmt.execute(sqlQuery);
            stmt.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}



