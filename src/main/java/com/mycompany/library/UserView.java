package com.mycompany.library;

import javax.swing.*;

public class UserView {
    public static void main(String userID) {
        JFrame frame = new JFrame("User View");
        JTextField searchBox = new JTextField();
        JButton add = new JButton("Add Book");
        JButton all = new JButton("All Books");
        JButton available = new JButton("Available Books");
        JButton update = new JButton("Update Book");
        JButton issued = new JButton("Issued Books");
        JButton returnBook = new JButton("Return Book");
        JButton logout = new JButton("Log out");
        add.setBounds(20, 20, 120, 25);
        all.setBounds(20, 60, 120, 25);
        available.setBounds(150, 20, 120, 25);
        update.setBounds(150, 60, 120, 25);
        issued.setBounds(280, 20, 160, 25);
        returnBook.setBounds(280, 60, 160, 25);
        logout.setBounds(450, 20, 120, 25);

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
        frame.add(returnBook);
        frame.add(logout);
        frame.setSize(500,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
