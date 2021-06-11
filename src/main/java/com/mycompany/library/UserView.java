package com.mycompany.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserView {
    public static void main(String userID) {
        JFrame frame = new JFrame("User View");
        JTextField searchBox = new JTextField();
        JButton all = new JButton("All Books");
        JButton add = new JButton("Add Book");
        JButton available = new JButton("Available Books");
        JButton update = new JButton("Update Book");
        JButton issued = new JButton("Issued List");
        JButton issue = new JButton("Issue Book");
        JButton returnBook = new JButton("Return Book");
        JButton logout = new JButton("Log out");
        all.setBounds(25, 20, 120, 25);
        add.setBounds(25, 60, 120, 25);
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
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE lib;");
                            statement.executeUpdate(
                                    "INSERT INTO books(title, author, genre, language, published, pages, quantity) VALUES('"
                                            + title + "','" + author + "','" + genre + "','" + language + "','"
                                            + published + "'," + pages + "," + quantity + ")");
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
        available.setBounds(285, 20, 140, 25);
        update.setBounds(435, 20, 120, 25);
        issue.setBounds(155, 60, 120, 25);
        issued.setBounds(155, 20, 120, 25);
        returnBook.setBounds(285, 60, 140, 25);
        logout.setBounds(435, 60, 120, 25);

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                Main.login();
            }
        });
        // Search book, author, genre

        // Add book

        // View All Books

        // View Issued Books

        // Logout

        frame.add(searchBox);
        frame.add(add);
        frame.add(all);
        frame.add(available);
        frame.add(issued);
        frame.add(update);
        frame.add(issue);
        frame.add(returnBook);
        frame.add(logout);
        frame.setSize(600, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
