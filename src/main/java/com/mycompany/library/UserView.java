package com.mycompany.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        available.setBounds(285, 20, 140, 25);
        update.setBounds(435, 20, 120, 25);
        issue.setBounds(155, 60, 120, 25);
        issued.setBounds(155, 20, 120, 25);
        returnBook.setBounds(285, 60, 140, 25);
        logout.setBounds(435, 60, 120, 25);

        logout.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent event){
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
        frame.setSize(600,150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
