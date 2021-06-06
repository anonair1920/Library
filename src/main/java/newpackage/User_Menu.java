package newpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class User_Menu {
    public static void user_menu(String UID) {
        JFrame f = new JFrame("User Function");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton view_btn = new JButton("View Books");
        view_btn.setBounds(20, 20, 120, 25);
        view_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("Books Available");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM books";
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        JButton my_book = new JButton("My Library");
        my_book.setBounds(150, 20, 120, 25);
        my_book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("My Library");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                int UID_int = Integer.parseInt(UID);
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT DISTINCT issued.*, books.title, books.genre, books.price FROM issued, books "
                        + "WHERE ((issued.users_id = " + UID_int + ") AND ( books.id FROM issued WHERE issued.users_id ="
                        + UID_int + "))) GROUP BY issued_id";
                String sql1 = "SELECT books_id FROM issued WHERE users_id = " + UID_int;
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ArrayList books_list = new ArrayList();

                    ResultSet rs = statement.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));
                    JScrollPane scrollPane = new JScrollPane(book_list);
                  
                    f.add(scrollPane);
                    f.setSize(800,400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        });
        f.add(my_book);
        f.add(view_btn);
        f.setSize(300,100);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
