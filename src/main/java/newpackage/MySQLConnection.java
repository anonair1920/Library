package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public static Connection connect() {
        String name,pass,url;
        try {
            name = "root";
            pass = "TNNgoan1412";
            url = "jdbc:mysql://localhost:3307/library?useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,name,pass);
            System.out.println("Database connected!"); 
            return connection;         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}