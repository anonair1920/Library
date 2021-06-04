/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import javax.swing.*;

/**
 *
 * @author Admin
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        login();
    }
    public static void login(){
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
                String password = tf_password.getPassword();

                if ( username.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Please enter user name!");
                } else if ( password.euqals("") ) {
                    JOptionPane.showMessageDialog(null, "Please enter password!");
                } else {
                    Connection connection = connect(); // connect to database
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("Use Library"); //use database with the name "Library"
                        String str = ("SELECT * FROM users WHERE user_name = '" + username + "' AND password = '"+ password +"' "); //retrieve username & password from users database
                        ResultSet rs = statement.executeQuery(str);

                        if ( rs.next() == false ) {
                            System.out.println("User not found!");
                            JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
                        } else {
                            // I don't understand where f comes from??
                            // if dispose() is to clear things inside the txtfield then it's supposed to be tf_username.dispose()??
                            f.dispose();
                            rs.beforeFirst(); // move the pointer above
                            while ( rs.next() ){
                                String admin = rs.getString("Admin");
                                String UID = rs.getString("UID");
                                if ( admin.equals("1") ){
                                    admin_menu();
                                } else {
                                    user_menu(UID);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
}
