package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/taskjdbc?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "5432";
    private static Properties hibernateProps;

    static {
        hibernateProps= new Properties();

        //Конфигурация базы данных
        hibernateProps.put("hibernate.driver_class", Driver.class.getName());
        hibernateProps.put("hibernate.connection.url", URL);
        hibernateProps.put("hibernate.connection.username", USER);
        hibernateProps.put("hibernate.connection.password", PASSWORD);

        //Конфгурация самого Hibernate
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProps.put("hibernate.show_sql", "true");
        hibernateProps.put("hibernate.current_session_context_class", "thread");
    }

    public static Connection getConnection() throws SQLException {
        Class<Driver> driverClass = Driver.class;
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addProperties(hibernateProps).addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }

}
