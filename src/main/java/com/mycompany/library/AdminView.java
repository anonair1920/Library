package com.mycompany.library;

import javax.swing.*;

public class AdminView {
    public static void main() {
        JFrame frame = new JFrame("Admin View");
        JButton view = new JButton("View Users");
        JButton add = new JButton("Add User");
        JButton update = new JButton("Update User");
        JButton delete = new JButton("Delete User");
        JButton logout = new JButton("Logout");
     
        view.setBounds(20, 20, 120, 25);
        add.setBounds(20, 60, 120, 25);
        update.setBounds(150, 20, 120, 25);
        delete.setBounds(150, 60, 120, 25);
        logout.setBounds(20, 100, 250, 25);
        // view users

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
