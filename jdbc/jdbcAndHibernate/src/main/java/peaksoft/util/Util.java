package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String url = "jdbc:postgresql://localhost:5432/postgres";
    public static final String userName = "postgres";
    public static final String password = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    url,
                    userName,
                    password
            );
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;

    }
}
