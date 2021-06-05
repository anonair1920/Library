package newpackage;

import java.sql.DriverManager;
import java.sql.Statement;

public class Connection {
    public static Connection connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=");
            return (Connection) connection;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getMetaData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}