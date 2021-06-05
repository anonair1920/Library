package newpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login {
    public static void login() {
        JFrame f = new JFrame("Login"); // instance of JFrame
        //create labels Username & Password
        JLabel l1, l2;
        l1 = new JLabel("User name");
        l1.setBounds(30,15,100,30); // x,y,width,height
        l2 = new JLabel("Password");
        l2.setBounds(30,50,100,30); 
        //create txt fields for username & password
        JTextField tf_username = new JTextField();
        tf_username.setBounds(110,15,200,30);
        JPasswordField tf_password = new JPasswordField();
        tf_password.setBounds(110,50,200,30);
        // create button
        JButton login_btn = new JButton("Login");
        login_btn.setBounds(130,90,80,25);
        login_btn.addActionListener(new ActionListener() {
            // function performs action login
            public void actionPerformed( ActionEvent event ) {
                //store user input into Strings
                String username = tf_username.getText();
                String password = tf_password.getText();
    
                if ( username.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Please enter user name!");
                } else if ( password.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Please enter password!");
                } else {
                    Connection connection = MySQLConnection.connect(); // connect to database
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("Use Library"); //use database with the name "Library"
                        String str = ("SELECT * FROM users WHERE user_name = '" + username + "' AND password = '"+ password +"' "); //retrieve username & password from users database
                        ResultSet rs = statement.executeQuery(str);
    
                        if ( rs.next() == false ) {
                            System.out.println("User not found!");
                            JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
                        } else {
                        f.dispose();
                            rs.beforeFirst(); // move the pointer above
                            while ( rs.next() ){
                                String admin = rs.getString("Admin");
                                String UID = rs.getString("UID");
                                if ( admin.equals("1") ){
                                    // admin_menu();
                                } else {
                                    // user_menu(UID);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }      
        });
        f.add(tf_password);
        f.add(login_btn);
        f.add(tf_username);
        f.add(l1);
        f.add(l2);
        f.setSize(400, 180);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }    
}