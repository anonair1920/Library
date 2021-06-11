package com.mycompany.library;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        login();
    }

    public static void login() {
        JFrame frame = new JFrame("Login");
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setBounds(30, 15, 100, 30);
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(30, 50, 100, 30);
        JTextField tf = new JTextField();
        tf.setBounds(110, 15, 200, 30);
        JPasswordField pf = new JPasswordField();
        pf.setBounds(110, 50, 200, 30);
        JButton btn = new JButton("Login");
        btn.setBounds(130, 90, 80, 25);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String username = tf.getText();
                String password = pf.getText();
                if (username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username & password!");
                } else {
                    Connection connection = connect();
                    try {
                        Statement statement = connection.createStatement();
                        if (statement == null) {
                            JOptionPane.showMessageDialog(null, "Connection not found!");
                            return;
                        }
                        statement.executeUpdate("USE lib;");
                        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '"
                                + password + "';";
                        ResultSet result = statement.executeQuery(query);
                        if (!result.next()) {
                            System.out.println("Login unsuccessful!");
                            JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
                        } else {
                            System.out.println("Login!");
                            JOptionPane.showMessageDialog(null, "Welcome to Library!");
                            frame.dispose();
                            result = statement.executeQuery(query);
                            while (result.next()) {
                                String type = result.getString("admin");
                                String userID = result.getString("id");
                                if (type.equals("1")) {
                                    AdminView.main();
                                } else {
                                    UserView.main(userID);
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        frame.add(labelUsername);
        frame.add(labelPassword);
        frame.add(tf);
        frame.add(pf);
        frame.add(btn);
        frame.setSize(360, 180);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static Connection connect() {
        String name = "root";
        String pass = "TNNgoan1412";
        String url = "jdbc:mysql://localhost:3307/lib?useSSL=false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, pass);
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void logout(){
        login();
    }

}
