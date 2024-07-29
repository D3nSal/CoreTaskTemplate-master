package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/taskjdbc?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "5432";

    public static Connection getConnection() throws SQLException {
        Class<Driver> driverClass = Driver.class;
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
