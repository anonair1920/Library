package com.mycompany.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminView {
    public static void main() {
        JFrame frame = new JFrame("Admin View");
        JButton view = new JButton("View Users");
        JButton add = new JButton("Add User");
        JButton update = new JButton("Update User");
        JButton delete = new JButton("Delete User");
        JButton logout = new JButton("Logout");
        view.setBounds(20, 20, 120, 25);
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Users List");
                Connection connection = Main.connect();
                String query = "SELECT * FROM users WHERE admin = 1;";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE lib;");
                    statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("User ID");
                    model.addColumn("Name");
                    model.addColumn("Password");
                    model.addColumn("Email");
                    while (result.next()) {
                        model.addRow(new Object[] { result.getString(1), result.getString(2), result.getString(3),
                                result.getString(5) });
                    }
                    JTable usersList = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(usersList);
                    frame.add(scrollPane);
                    frame.setSize(600, 300);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Enter User Details");
                JLabel l1 = new JLabel("Username");
                l1.setBounds(30, 15, 100, 30);
                JLabel l2 = new JLabel("Password");
                l2.setBounds(30, 50, 100, 30);
                JLabel l3 = new JLabel("Email");
                l3.setBounds(30, 85, 100, 30);
                JTextField user = new JTextField();
                user.setBounds(110, 15, 200, 30);
                JPasswordField pass = new JPasswordField();
                pass.setBounds(110, 50, 200, 30);
                JTextField mail = new JTextField();
                mail.setBounds(110, 85, 200, 30);
                JRadioButton u1 = new JRadioButton("Admin");
                u1.setBounds(55, 80, 200, 30);
                JRadioButton u2 = new JRadioButton("User");
                u2.setBounds(130, 80, 200, 30);
                ButtonGroup btngr = new ButtonGroup();
                btngr.add(u1);
                btngr.add(u2);
                JButton btn = new JButton("CREATE");
                btn.setBounds(130, 130, 80, 25);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String username = user.getText();
                        String password = pass.getText();
                        String email = mail.getText();
                        Boolean admin = false;
                        if (u1.isSelected()) {
                            admin = true;
                        }
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library;");
                            statement.executeUpdate("INSERT INTO users(username, password, email, admin) VALUES('" + username
                                    + "','" + password + "','"+email+"'," + admin + ");");
                            JOptionPane.showMessageDialog(null, "User added!");
                            System.out.println("Added new user!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
            }
        });
        add.setBounds(20, 60, 120, 25);
        update.setBounds(150, 20, 120, 25);
        delete.setBounds(150, 60, 120, 25);
        logout.setBounds(20, 100, 250, 25);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Connection connection = Main.connect();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library"); // use database with the name
                    frame.dispose();
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        // view user

        // Add a user

        // Update user info

        // Delete user

        // Logout
        frame.add(view);
        frame.add(add);
        frame.add(update);
        frame.add(delete);
        frame.add(logout);
        frame.setSize(310, 180);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
