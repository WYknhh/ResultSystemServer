package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection CONN() throws ClassNotFoundException, SQLException {
        final String URL = "jdbc:mysql://116.62.5.153:3306";
        final String USER = "root";
        final String PASSWORD = "WYKwyk123";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://116.62.5.153:3306/results?useSSL=false&serverTimezone=UTC",USER,PASSWORD);
        return conn;

    }
}
