package utility;

import com.github.javafaker.Faker;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.openqa.selenium.WebDriver;
import pages.SignUpPage;

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
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader(sqlScriptPath));
            sr.runScript(reader);
        } catch (SQLException | FileNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public RegistrationData createAccByRole(WebDriver driver, int role)    {
        RegistrationFacade registrationFacade = new RegistrationFacade(new Faker(), new RegDataBuilder(), new SignUpPage(driver));
        RegistrationData user = registrationFacade.registerUserAccount(driver);

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            String sql = "UPDATE users" +
                    " SET role_id=?" +
                    " WHERE email=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, role);
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return user;
    }

    public void deleteUserByEmail(String email) {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            String sql = "DELETE FROM users" +
                    " WHERE email=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public RegistrationData createRestaurant()    {
        Faker faker = new Faker();
        RegDataBuilder regDataBuilder = new RegDataBuilder();

        RegistrationData restaurant = regDataBuilder
                .name(faker.superhero().name())
                .address(faker.address().fullAddress())
                .build();

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            String sql = "INSERT INTO restaurants (name, address_id, owner_id, status)" +
                    " VALUES (?, ?, 1, 0)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setString(2, restaurant.getAddress());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return restaurant;
    }

    public void deleteRestaurantByName(String restName) {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            String sql = "DELETE FROM restaurants" +
                    " WHERE name=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, restName);
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public int createOrderID(RegistrationData waiter)   {
        int orderID =0;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {

            int waiterID = 0;
            String sqlForWaiterID = "SELECT id FROM users" +
                    " WHERE email=?";

            PreparedStatement preparedStatementForWaiterID = connection.prepareStatement(sqlForWaiterID);
            preparedStatementForWaiterID.setString(1, waiter.getEmail());
            ResultSet resultForWaiterID = preparedStatementForWaiterID.executeQuery();

            if(resultForWaiterID.next())    {
                waiterID = resultForWaiterID.getInt("id");
            }

            String sql = "INSERT INTO orders(status, user_id, waiter_id, rest_id)" +
                    " VALUES (?, 1, ?, 1)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Assigned waiter");
            preparedStatement.setInt(2, waiterID);
            preparedStatement.execute();

            String sqlForOrderID = "SELECT MAX(id) AS order_id FROM orders";

            PreparedStatement preparedStatementForOrderID = connection.prepareStatement(sqlForOrderID);
            ResultSet resultForOrderID = preparedStatementForOrderID.executeQuery();

            if(resultForOrderID.next())    {
                orderID = resultForOrderID.getInt("order_id");
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return orderID;
    }

    public void deleteOrderByID(int orderID)    {
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))  {
            String sql = "DELETE FROM orders" +
                    " WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}



