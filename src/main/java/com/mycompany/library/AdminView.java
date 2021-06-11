package com.mycompany.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminView {
    public static void main() {
        JFrame frame = new JFrame("Admin View");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton view = new JButton("View Users");
        JButton add = new JButton("Add User");
        JButton update = new JButton("Update User");
        JButton delete = new JButton("Delete User");
        JButton logout = new JButton("Logout");
        view.setBounds(20, 20, 120, 25);
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new JFrame("Users List");
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection connection = Main.connect();
                String query = "SELECT DISTINCT users.id, users.username, users.email, users.password FROM users WHERE admin = 0;";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE lib;");
                    statement = connection.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("User ID");
                    model.addColumn("Name");
                    model.addColumn("Email");
                    model.addColumn("Password");
                    while (result.next()) {
                        model.addRow(new Object[] { result.getString(1), result.getString(2), result.getString(4),
                                result.getString(3) });
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
        add.setBounds(20, 60, 120, 25);
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
                u1.setBounds(110, 115, 70, 30);
                JRadioButton u2 = new JRadioButton("User");
                u2.setBounds(200, 115, 70, 30);
                ButtonGroup btngr = new ButtonGroup();
                btngr.add(u1);
                btngr.add(u2);
                JButton btn = new JButton("CREATE");
                btn.setBounds(130, 155, 80, 25);
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
                                statement.executeUpdate("USE lib;");
                                statement.executeUpdate("INSERT INTO users(username, password, email, admin) VALUES('"
                                        + username + "','" + password + "','" + email + "'," + admin + ");");
                                JOptionPane.showMessageDialog(null, "User added!");
                                System.out.println("Added new user!"+admin);
                                frame.dispose();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                });
                frame.add(btn);
                frame.add(u2);
                frame.add(u1);
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(user);
                frame.add(pass);
                frame.add(mail);
                frame.setSize(355, 240);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        update.setBounds(150, 20, 120, 25);
        update.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame frame = new JFrame("Update user informations");
                JLabel l1 = new JLabel("New Username");
                JLabel l2 = new JLabel("New Password");
                JLabel l3 = new JLabel("New Email");
                JLabel l4 = new JLabel("userID");
                JTextField t1 = new JTextField("");
                JTextField t2 = new JTextField("");
                JTextField t3 = new JTextField("");
                JTextField t4 = new JTextField("");
                JButton btn = new JButton("SUBMIT");
                l1.setBounds(30, 50, 140, 30);
                l2.setBounds(30, 85, 140, 30);
                l3.setBounds(30, 120, 140, 30);
                l4.setBounds(30, 15, 140, 30);
                t1.setBounds(172, 50, 180, 30);
                t2.setBounds(172, 85, 180, 30);
                t3.setBounds(172, 120, 180, 30);
                t4.setBounds(172, 15, 180, 30);
                btn.setBounds(150, 175, 80, 30);
                btn.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent event){
                        String id = t4.getText();
                        String username = t1.getText();
                        String password = t2.getText();
                        String email = t3.getText();
                        Connection connection = Main.connect();
                        try {
                            Statement st = connection.createStatement();
                            st.executeUpdate("USE lib;");
                            st.executeUpdate("UPDATE users SET username = '" + username + "', password = '"+password+"',email = '"+email+"' WHERE id = "+id+";");
                            JOptionPane.showMessageDialog(null, "Data Updated!");
                            System.out.println("Update database!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                            e.printStackTrace();
                        }
                    }
                });
                frame.add(l1);
                frame.add(l2);
                frame.add(l3);
                frame.add(l4);
                frame.add(t1);
                frame.add(t2);
                frame.add(t3);
                frame.add(t4);
                frame.add(btn);
                frame.setSize(400,250);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        delete.setBounds(150, 60, 120, 25);
        delete.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame frame = new JFrame("Delete user by ID");
                JLabel l = new JLabel("User ID");
                JTextField t = new JTextField();
                JButton btn = new JButton("DELETE");
                l.setBounds(30, 20, 70, 25);
                t.setBounds(105, 20, 160, 25);
                btn.setBounds(115, 80, 80, 25);
                btn.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String id = t.getText();
                        Connection connection = Main.connect();
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE lib;");
                            statement.executeUpdate("DELETE FROM users WHERE id = " + id + ";");
                            System.out.println("passed!");
                            System.out.println("DELETE FROM users WHERE id = " + id + ";");
                            JOptionPane.showMessageDialog(null, "User is removed from data!");
                            System.out.println("Deleted!");
                            frame.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                            e.printStackTrace();
                        }
                    }
                });
                frame.add(l);
                frame.add(t);
                frame.add(btn);
                frame.setSize(320, 180);
                frame.setLayout(null);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        logout.setBounds(20, 100, 250, 25);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Connection connection = Main.connect();
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library"); // use database with the name
                    frame.dispose();
                } catch (SQLException e) {
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
