package newpackage;

import java.sql.Statement;
import java.sql.*;

public class Create {
    public static void create() {
        try {
            Connection connection = MySQLConnection.connect();
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            while ( resultSet.next() ){
                String databaseName = resultSet.getString(1);
                if ( databaseName.equals("library")) {
                    Statement statement = connection.createStatement();
                    String sql = "DROP DATABASE library";
                    statement.executeUpdate(sql);
                }
            }
            Statement statement = connection.createStatement();
            String database = "CREATE DATABASE Library";
            statement.executeUpdate(database);
            statement.executeUpdate("USE library");
            String create_table_users = "CREATE TABLE users(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(30) NOT NULL, password VARCHAR(2) NOT NULL)";
            String create_table_books = "CREATE TABLE books(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(50), published YEAR);";
            String create_table_issued = "CREATE TABLE issued();";
            statement.executeUpdate(create_table_users);
            statement.executeUpdate(create_table_books);
            statement.executeUpdate(create_table_issued);
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}