package com.mycompany.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mycompany.library.Main.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class UserView {
    public static void main(String userID) {
        JFrame frame = new JFrame("User View");
        JTextField searchBox = new JTextField("The Little Prince?");
        JButton search = new JButton("Search");
        JButton all = new JButton("All Books");
        JButton add = new JButton("Add Book");
        JButton available = new JButton("Available Books");
        JButton update = new JButton("Update Book");
        JButton issued = new JButton("Issued List");
        JButton issue = new JButton("Issue Book");
        JButton returnBook = new JButton("Return Book");
        JButton logout = new JButton("Log out");
        JButton delete = new JButton("Delete Book");
        searchBox.setBounds(25, 20, 400, 25);
        search.setBounds(435, 20, 120, 25);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Result from search");
                String keyword = searchBox.getText();
                String query = "SELECT * FROM books WHERE title = '" + keyword + "';";
                Connection connection = Main.connect();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID");
                    model.addColumn("Title");
                    model.addColumn("Author");
                    model.addColumn("Genre");
                    model.addColumn("Published");
                    model.addColumn("Language");
                    model.addColumn("Pages");
                    model.addColumn("Quantity");
                    while (result.next()) {
                        model.addRow(new Object[] { result.getString(1), result.getString(2), result.getString(3),
                                result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                                result.getString(8) });
                    }
                    JTable tb = new JTable(model);
                    JScrollPane sp = new JScrollPane(tb);
                    frame.add(sp);
                    frame.setSize(1200, 500);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        all.setBounds(25, 70, 120, 25);
        all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Library");
                Connection connection = Main.connect();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE lib;");
                    ResultSet result = statement.executeQuery("SELECT * FROM books;");
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID");
                    model.addColumn("Title");
                    model.addColumn("Author");
                    model.addColumn("Genre");
                    model.addColumn("Published");
                    model.addColumn("Language");
                    model.addColumn("Pages");
                    model.addColumn("Quantity");
                    while (result.next()) {
                        model.addRow(new Object[] { result.getString(1), result.getString(2), result.getString(3),
                                result.getString(4), result.getString(5), result.getString(6), result.getString(7),
                                result.getString(8) });
                    }
                    JTable tb = new JTable(model);
                    JScrollPane sp = new JScrollPane(tb);
                    frame.add(sp);
                    frame.setSize(1200, 500);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        add.setBounds(25, 110, 120, 25);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Add Book Details");
                JLabel l1 = new JLabel("Title");
                JLabel l2 = new JLabel("Author");
                JLabel l3 = new JLabel("Genre");
                JLabel l4 = new JLabel("Language");
                JLabel l5 = new JLabel("Published");
                JLabel l6 = new JLabel("Pages");
                JLabel l7 = new JLabel("Quantity");
                JTextField t1 = new JTextField();
                JTextField t2 = new JTextField();
                JTextField t3 = new JTextField();
                JTextField t4 = new JTextField();
                JTextField t5 = new JTextField();
                JTextField t6 = new JTextField();
                JTextField t7 = new JTextField();
                JButton btn = new JButton("ADD");
                l1.setBounds(30, 20, 70, 25);
                l2.setBounds(30, 60, 70, 25);
                l3.setBounds(30, 100, 70, 25);
                l4.setBounds(30, 140, 70, 25);
                l5.setBounds(30, 180, 70, 25);
                l6.setBounds(30, 220, 70, 25);
                l7.setBounds(30, 260, 70, 25);
                t1.setBounds(100, 20, 200, 25);
                t2.setBounds(100, 60, 200, 25);
                t3.setBounds(100, 100, 200, 25);
                t4.setBounds(100, 140, 200, 25);
                t5.setBounds(100, 180, 200, 25);
                t6.setBounds(100, 220, 200, 25);
                t7.setBounds(100, 260, 200, 25);
                btn.setBounds(130, 305, 80, 25);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String title = t1.getText();
                        String author = t2.getText();
                        String genre = t3.getText();
                        String language = t4.getText();
                        String published = t5.getText();
                        String pages = t6.getText();
                        String quantity = t7.getText();
                        String query = "INSERT INTO books(title, author, genre, language, published, pages, quantity) VALUES('"
                                + title + "','" + author + "','" + genre + "','" + language + "','" + published + "',"
                                + pages + "," + quantity + ")";
                        String autoUpdate = "UPDATE books SET available = " + quantity + ", issued = 0 WHERE title = '"
                                + title + "';";
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);
                            statement.executeUpdate(autoUpdate);
                            JOptionPane.showMessageDialog(null, "Book added!");
                            System.out.println("Added new book!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(l4);
                frame.add(l5);
                frame.add(l6);
                frame.add(l7);
                frame.add(t1);
                frame.add(t2);
                frame.add(t3);
                frame.add(t4);
                frame.add(t5);
                frame.add(t6);
                frame.add(t7);
                frame.add(btn);
                frame.setSize(350, 385);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        available.setBounds(285, 70, 140, 25);
        available.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame frame = new JFrame("Available Books");
                Connection connection = Main.connect();
                String query = "SELECT * FROM books WHERE available > 0";
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Issue ID");
                    model.addColumn("Student Name");
                    model.addColumn("Book Title");
                    model.addColumn("BookID");
                    model.addColumn("Issued Date");
                    model.addColumn("Period");
                    while (rs.next()) {
                        model.addRow(new Object[] { rs.getString(1), rs.getString(3), rs.getString(9), rs.getString(2),
                                rs.getString(6), rs.getString(7) });
                    }
                    JTable table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);
                    frame.add(scrollPane);
                    frame.setSize(1200, 600);
                    frame.setVisible(true);
                    frame.setLayout(null);
                    frame.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        update.setBounds(435, 70, 120, 25);
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Enter details");
                JLabel l1 = new JLabel("Book ID");
                JLabel l2 = new JLabel("Book Title");
                JLabel l3 = new JLabel("Book Quantity");
                JTextField t1 = new JTextField();
                JTextField t2 = new JTextField();
                JTextField t3 = new JTextField();
                JButton more = new JButton("More");
                JButton submit = new JButton("Submit");
                l1.setBounds(30, 20, 100, 25);
                l2.setBounds(30, 60, 100, 25);
                l3.setBounds(30, 100, 100, 25);
                t1.setBounds(130, 20, 120, 25);
                t2.setBounds(130, 60, 120, 25);
                t3.setBounds(130, 100, 120, 25);
                more.setBounds(50, 150, 80, 25);
                submit.setBounds(150, 150, 80, 25);
                more.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JFrame frame = new JFrame("Update more information");
                        JLabel l1 = new JLabel("Book Title");
                        JLabel l2 = new JLabel("Author");
                        JLabel l3 = new JLabel("Genre");
                        JLabel l4 = new JLabel("Pulished");
                        JLabel l5 = new JLabel("Language");
                        JLabel l6 = new JLabel("Total Pages");
                        JLabel l7 = new JLabel("Quantity");
                        JLabel l8 = new JLabel("Book ID");
                        JTextField t1 = new JTextField();
                        JTextField t2 = new JTextField();
                        JTextField t3 = new JTextField();
                        JTextField t4 = new JTextField();
                        JTextField t5 = new JTextField();
                        JTextField t6 = new JTextField();
                        JTextField t7 = new JTextField();
                        JTextField t8 = new JTextField();
                        JButton submit = new JButton("Submit");
                        l1.setBounds(30, 20, 100, 25);
                        l2.setBounds(30, 60, 100, 25);
                        l3.setBounds(30, 100, 100, 25);
                        l4.setBounds(30, 140, 100, 25);
                        l5.setBounds(30, 180, 100, 25);
                        l6.setBounds(30, 220, 100, 25);
                        l7.setBounds(30, 260, 100, 25);
                        l8.setBounds(30, 300, 100, 25);
                        t1.setBounds(130, 20, 150, 25);
                        t2.setBounds(130, 60, 150, 25);
                        t3.setBounds(130, 100, 150, 25);
                        t4.setBounds(130, 140, 150, 25);
                        t5.setBounds(130, 180, 150, 25);
                        t6.setBounds(130, 220, 150, 25);
                        t7.setBounds(130, 260, 150, 25);
                        t8.setBounds(130, 300, 150, 25);
                        submit.setBounds(120, 350, 80, 25);
                        submit.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent event) {
                                String s1 = t1.getText();
                                String s2 = t2.getText();
                                String s3 = t3.getText();
                                String s4 = t4.getText();
                                String s5 = t5.getText();
                                String s6 = t6.getText();
                                String s7 = t7.getText();
                                String id = t8.getText();
                                String query = "UPDATE books SET title = '" + s1 + "', author = '" + s2 + "', genre = '"
                                        + s3 + "', published = '" + s4 + "', language = '" + s5 + "', pages = " + s6
                                        + ", quantity = " + s7 + " WHERE id = " + id + " ;";
                                Connection connection = Main.connect();
                                try {
                                    Statement statement = connection.createStatement();
                                    statement.executeUpdate("USE lib;");
                                    statement.executeUpdate(query);
                                    JOptionPane.showMessageDialog(null, "Book updated!");
                                    System.out.println("Updated book!");
                                    frame.dispose();
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null, e);
                                }
                            }
                        });
                        frame.add(l1);
                        frame.add(l2);
                        frame.add(l3);
                        frame.add(l4);
                        frame.add(l5);
                        frame.add(l6);
                        frame.add(l7);
                        frame.add(l8);
                        frame.add(t1);
                        frame.add(t2);
                        frame.add(t3);
                        frame.add(t4);
                        frame.add(t5);
                        frame.add(t6);
                        frame.add(t7);
                        frame.add(t8);
                        frame.add(submit);
                        frame.setSize(330, 430);
                        frame.setLayout(null);
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);

                    }
                });
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String s1 = t1.getText();
                        String s2 = t2.getText();
                        String s3 = t3.getText();
                        String query = "UPDATE books SET title = '" + s2 + "', quantity = " + s3 + " WHERE id = " + s1
                                + ";";
                        Connection connection = Main.connect();
                        try {
                            Statement st = connection.createStatement();
                            st.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Update successfully!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                frame.dispose();
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(t1);
                frame.add(t2);
                frame.add(t3);
                frame.add(more);
                frame.add(submit);
                frame.setSize(300, 230);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        issue.setBounds(155, 70, 120, 25);
        issue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Issue a book");
                JLabel l1 = new JLabel("Student Name");
                JLabel l2 = new JLabel("Student ID");
                JLabel l3 = new JLabel("Book ID");
                JLabel l4 = new JLabel("Date");
                JLabel l5 = new JLabel("Period");
                JTextField t1 = new JTextField();
                JTextField t2 = new JTextField();
                JTextField t3 = new JTextField();
                JTextField t4 = new JTextField();
                JTextField t5 = new JTextField();
                JButton button = new JButton("ISSUE");
                l1.setBounds(30, 30, 100, 25);
                l2.setBounds(30, 70, 100, 25);
                l3.setBounds(30, 110, 100, 25);
                l4.setBounds(30, 150, 100, 25);
                l5.setBounds(30, 190, 100, 25);
                t1.setBounds(130, 30, 200, 25);
                t2.setBounds(130, 70, 200, 25);
                t3.setBounds(130, 110, 200, 25);
                t4.setBounds(130, 150, 200, 25);
                t5.setBounds(130, 190, 200, 25);
                button.setBounds(150, 235, 80, 25);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String s1 = t1.getText();
                        String s2 = t2.getText();
                        String s3 = t3.getText();
                        String s4 = t4.getText();
                        String s5 = t5.getText();
                        Connection connection = Main.connect();
                        String query = "INSERT INTO issued(studentName, studentID, bookID, issuedDate, period) VALUES('"
                                + s1 + "'," + s2 + "," + s3 + ",'" + s4 + "'," + s5 + ");";
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE lib;");
                            statement.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Book is issued!");
                            System.out.println("issued");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(l4);
                frame.add(l5);
                frame.add(t1);
                frame.add(t2);
                frame.add(t3);
                frame.add(t4);
                frame.add(t5);
                frame.add(button);
                frame.setSize(380, 330);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        issued.setBounds(155, 110, 120, 25);
        issued.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Issued books");
                Connection connection = Main.connect();
                String query = "SELECT DISTINCT issued.*, books.title FROM issued, books WHERE issued.bookID = books.id;";
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Issue ID");
                    model.addColumn("Student Name");
                    model.addColumn("Book Title");
                    model.addColumn("BookID");
                    model.addColumn("Issued Date");
                    model.addColumn("Period");
                    while (rs.next()) {
                        model.addRow(new Object[] { rs.getString(1), rs.getString(3), rs.getString(9), rs.getString(2),
                                rs.getString(6), rs.getString(7) });
                    }
                    JTable table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);
                    frame.add(scrollPane);
                    frame.setSize(1200, 600);
                    frame.setVisible(true);
                    frame.setLayout(null);
                    frame.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        returnBook.setBounds(285, 110, 140, 25);
        returnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Enter Details");
                JLabel l1 = new JLabel("Student ID");
                JLabel l2 = new JLabel("Book ID");
                JLabel l3 = new JLabel("Return Date");
                JTextField t1 = new JTextField();
                JTextField t2 = new JTextField();
                JTextField t3 = new JTextField();
                JButton btn = new JButton("Return");
                l1.setBounds(30, 20, 100, 25);
                l2.setBounds(30, 60, 100, 25);
                l3.setBounds(30, 100, 100, 25);
                t1.setBounds(130, 20, 120, 25);
                t2.setBounds(130, 60, 120, 25);
                t3.setBounds(130, 100, 120, 25);
                btn.setBounds(100, 145, 80, 25);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String studentID = t1.getText();
                        String bookID = t2.getText();
                        String returnDate = t3.getText();
                        String query = "SELECT issuedDate FROM issued WHERE id IN (SELECT DISTINCT issued.id FROM issued WHERE studentID = "
                                + studentID + " AND bookID = " + bookID + ");";
                        String updateReturnDate = "UPDATE issued SET returnDate = '" + returnDate
                                + "' WHERE studentID = " + studentID + " AND bookID = " + bookID + ";";
                        String getPeriod = "SELECT DISTINCT issued.period FROM issued WHERE id IN (SELECT DISTINCT issued.id FROM issued WHERE studentID = "
                                + studentID + " AND bookID = " + bookID + ");";
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            String date1 = "";
                            String date2 = returnDate;
                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()) {
                                date1 = rs.getString(1);
                            }
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate d1 = LocalDate.parse(date1, dtf);
                            LocalDate d2 = LocalDate.parse(date2, dtf);
                            long dif = Duration.between(d1.atStartOfDay(), d2.atStartOfDay()).toDays();
                            ex.days = (int) (TimeUnit.DAYS.convert(dif, TimeUnit.DAYS));
                            statement.executeUpdate(updateReturnDate);
                            frame.dispose();
                            ResultSet result = statement.executeQuery(getPeriod);
                            String period;
                            while (result.next()) {
                                period = result.getString(1);
                                int i = Integer.parseInt(period);
                                int diff = (int) dif;
                                if (i < diff) {
                                    int fine = (diff - i) * 2;
                                    JOptionPane.showMessageDialog(null, "Fine:   $" + fine);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Book returned!");
                                }
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(t1);
                frame.add(t2);
                frame.add(t3);
                frame.add(btn);
                frame.setSize(280, 230);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        logout.setBounds(435, 110, 120, 25);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                Main.login();
            }
        });
        delete.setBounds(25, 110, 120, 25);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Delete by ID");
                JLabel label = new JLabel("Book ID");
                JTextField textField = new JTextField();
                JButton btn = new JButton("Delete");
                label.setBounds(30, 25, 70, 25);
                textField.setBounds(100, 25, 70, 25);
                btn.setBounds(60, 65, 80, 25);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String id = textField.getText();
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE lib;");
                            statement.executeUpdate("DELETE FROM books WHERE id =" + id);
                            JOptionPane.showMessageDialog(null, "Book is removed from Library!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                frame.add(label);
                frame.add(textField);
                frame.add(btn);
                frame.setSize(220, 140);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        frame.dispose();
        frame.add(searchBox);
        frame.add(search);
        frame.add(add);
        frame.add(all);
        frame.add(available);
        frame.add(issued);
        frame.add(update);
        frame.add(issue);
        frame.add(returnBook);
        frame.add(logout);
        frame.add(delete);
        frame.setSize(600, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
