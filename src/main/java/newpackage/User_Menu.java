package newpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class User_Menu {
    public static void user_menu(String UID) {
        System.out.println(UID);
        JFrame f = new JFrame("User Function");
        JButton view_btn = new JButton("View Books");
        view_btn.setBounds(20, 20, 120, 25);
        view_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("Library");

                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM books";
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Book ID");
                    model.addColumn("Title");
                    model.addColumn("Author");
                    model.addColumn("Published");
                    while (rs.next()) {
                        model.addRow(
                                new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
                    }
                    JTable book_list = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    f.add(scrollPane);
                    f.setSize(1200, 400);
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
                int UID_int = Integer.parseInt(UID);
                Connection connection = MySQLConnection.connect();
                String issuedBooksByUserID = "SELECT DISTINCT issued.*, books.title, books.author, books.published FROM issued, books "
                        + "WHERE ((issued.user_id = " + UID_int + ") AND (books.id IN (SELECT book_id FROM issued WHERE issued.user_id = "
                        + UID_int + "))) GROUP BY id;";
                // String getBookIDfromUserID = "SELECT book_id FROM issued WHERE user_id = " + UID_int;
                // String issuedBooksByID2 = "SELECT DISTINCT issued.*, books.title, books.author, books.published FROM issued, books WHERE ((issued.user_id = 2) AND (books.id IN (SELECT book_id FROM issued WHERE issued.user_id = 2))) GROUP BY id;";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(issuedBooksByUserID);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Book ID"); //3
                    model.addColumn("Book Title");//7
                    model.addColumn("Author");//8
                    model.addColumn("Published"); //9
                    model.addColumn("Issued Date");//4
                    model.addColumn("Period");//5
                    while (rs.next()) {
                        model.addRow(
                                new Object[] { rs.getString(3), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(4), rs.getString(5) });
                    }
                    JTable book_list = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    f.add(scrollPane);
                    f.setSize(1200, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        f.add(my_book);
        f.add(view_btn);
        f.setSize(300, 100);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
