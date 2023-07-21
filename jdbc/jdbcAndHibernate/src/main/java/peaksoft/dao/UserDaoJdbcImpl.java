package peaksoft.dao;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private Connection connection;
    public UserDaoJdbcImpl() {
        this.connection = Util.getConnection();

    }
    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "first_name varchar," +
                "last_name varchar," +
                "age smallInt);";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("The table was successfully deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(" +
                "first_name,last_name,age)" +
                "values(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User saved successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User by id " + id + "deleted successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<User> getAllUsers() {
        String sql = "select * from users";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table cleared successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}