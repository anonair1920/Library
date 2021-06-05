package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=");
            System.out.println("Database connected!");
            return connection;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
}