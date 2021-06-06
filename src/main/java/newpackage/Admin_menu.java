package newpackage;

import java.sql.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.google.protobuf.TextFormat.ParseException;

import newpackage.Main.ex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;

public class Admin_menu {
    public static void admin_menu(){
        JFrame f = new JFrame("Admin Funtions");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton create_btn = new JButton("Create");
        create_btn.setBounds(450,60,120,25);
        create_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                Create.create();
                JOptionPane.showMessageDialog(null, "Database Created!");
            }
        });
        JButton view_btn = new JButton("View Lirary");
        view_btn.setBounds(20,20,120,25);
        view_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame f = new JFrame("Books Available");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM books";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.result.setToTableModel(rs));
               
                    JScrollPane scrollPane = new JScrollPane(book_list);
               
                    f.add(scrollPane);
                    f.setSize(800,400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch ( SQLException e ) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        JButton users_btn = new JButton("View Users");
        users_btn.setBounds(150,20,120,25);
        users_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("Users List");
                Connection connection = MySQLConnection.connect();
                String sql = "SELCT * FROM users";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    JTable users_list = new JTable();
                    users_list.setModel(DbUtils.resultSetToTableModel(rs));
              
                    JScrollPane scrollPane = new JScrollPane(users_list);
              
                    f.add(scrollPane);
                    f.setSize(800,400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch ( SQLException e ) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        JButton issued_btn = new JButton("View Issued Books");
        issued_btn.setBounds(280,20,160,25);
        issued_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame f = new JFrame("Users List");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM issued";
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    JTable book_list = new JTable();
                    book_list.setModel(DbUtils.resultSetToTableModel(rs));
                 
                    JScrollPane scrollPane = new JScrollPane(book_list);
                  
                    f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch ( SQLException e ) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        JButton add_user = new JButton("Add User");
        add_user.setBounds(20,60,120,25);
        add_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame g = new JFrame("Enter User Details");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1, l2;
                l1 = new JLabel("Username");
                l1.setBounds(30,15,100,30);
                l2 = new JLabel("Password");
                l2.setBounds(30,50,100,30);

                JTextField tf_user = new JTextField();
                tf_user.setBounds(110,15,200,30);
                JTextField tf_password = new JTextField();
                tf_password.setBounds(110,50,200,30);
                JRadioButton u1 = new JRadioButton("Admin");
                u1.setBounds(55,80,200,30);
                JRadioButton u2 = new JRadioButton("User");
                u2.setBounds(130,80,200,30);
                ButtonGroup btngr = new ButtonGroup();
                btngr.add(u1);
                btngr.add(u2);

                JButton create_btn = new JButton("CREATE");
                create_btn.setBounds(130,130,80,25);
                create_btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event){
                        String username = tf_user.getText();
                        String password = tf_password.getText();
                        Boolean admin = false;
                        
                        if (u1.isSelected()){
                            admin = true;
                        }
                        Connection connection = MySQLConnection.connect();

                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            statement.executeUpdate("INSERT INTO users(name, password, type) VALUES('" + username + "','" + password + "'," + admin + ")");
                            JOptionPane.showMessageDialog(null, "User added!");
                            g.dispose();
                        } catch ( SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                g.add(create_btn);
                g.add(u2);
                g.add(u1);
                g.add(l1);
                g.add(l2);
                g.add(tf_user);
                g.add(tf_password);
                g.setSize(350,200);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
        JButton add_book = new JButton("Add Book");
        add_book.setBounds(150,60,120,25);
        add_book.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFrame g = new JFrame("Enter Book Details");
                JLabel l1, l2, l3;
                l1 = new JLabel("Title");
                l1.setBounds(30,15,100,30);
                l2 = new JLabel("Genre");
                l2.setBounds(30,53,100,30);
                l3 = new JLabel("Price");
                l3.setBounds(30,90,100,30);
                JTextField tf_title = new JTextField();
                tf_title.setBounds(110, 15, 200, 30);
                JTextField tf_genre = new JTextField();
                tf_genre.setBounds(110, 53, 200, 30);
                JTextField tf_price = new JTextField();
                tf_price.setBounds(110, 90, 200, 30);

                JButton create_btn = new JButton("Submit");
                create_btn.setBounds(130,130,80,25);
                create_btn.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent event ){
                        String title, genre, price;
                        title = tf_title.getText();
                        genre = tf_genre.getText();
                        price = tf_price.getText();
                        int price_int = Integer.parseInt(price);

                        Connection connection = MySQLConnection.connect();

                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            statement.executeUpdate("INSERT INTO books(title, genre, price) VALUES('" + title + "','" + genre + "'," + price_int + ")");
                            JOptionPane.showMessageDialog(null, "Book added!");
                            g.dispose();
                        } catch ( SQLException e ) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                g.add(l3);
                g.add(create_btn);
                g.add(l1);
                g.add(l2);
                g.add(tf_title);
                g.add(tf_genre);
                g.add(tf_price);
                g.setSize(350,200);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
        JButton issue_book = new JButton("Issue Book");
        issue_book.setBounds(450,20,120,25);
        issue_book.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1, l2, l3, l4;
                l1 = new JLabel("Book ID");
                l1.setBounds(30,15, 100,30);
                l2 = new JLabel("User ID");
                l2.setBounds(30,53, 100,30);
                l3 = new JLabel("Period(days)");
                l3.setBounds(30,90, 100,30);
                l4 = new JLabel("Issued Date");
                l4.setBounds(30,127, 150,30);
                JTextField tf_book_id, tf_user_id, tf_period_date, tf_issued_date;
                tf_book_id = new JTextField();
                tf_book_id.setBounds(110, 15, 200, 30);
                tf_user_id = new JTextField();
                tf_user_id.setBounds(110, 53, 200, 30);
                tf_period_date = new JTextField();
                tf_period_date.setBounds(110, 90, 200, 30);
                tf_issued_date = new JTextField();
                tf_issued_date.setBounds(180,130,130,30);
                JButton create_btn = new JButton("Submit");
                create_btn.setBounds(130,170,80,25);
                create_btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        String book_id, user_id, period, issued_date;
                        book_id = tf_book_id.getText();
                        user_id = tf_user_id.getText();
                        period = tf_period_date.getText();
                        issued_date = tf_issued_date.getText();

                        int period_int = Integer.parseInt(period);

                        Connection connection = MySQLConnection.connect();

                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            statement.executeUpdate("INSERT INTO issued(user_id, book_id, issued_date, period) VALUES('" + user_id + "','" + book_id + "','" + issued_date + "'," + period_int + ")" );
                            JOptionPane.showMessageDialog(null, "Book Issued!");
                            g.dispose();
                        } catch ( SQLException e ) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                g.add(l3);
                g.add(l4);
                g.add(create_btn);
                g.add(l1);
                g.add(l2);
                g.add(tf_user_id);
                g.add(tf_book_id);
                g.add(tf_period_date);
                g.add(tf_issued_date);
                g.setSize(350,250);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
        JButton return_book = new JButton("Return Book");
        return_book.setBounds(280,60,160,25);
        return_book.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JFrame g = new JFrame("Enter Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1, l2;
                l1 = new JLabel("Issue ID");
                l1.setBounds(30,15,100,30);
                l2 = new JLabel("Return Date");
                l2.setBounds(30,50,150,30);
                JTextField tf_issue_id = new JTextField();
                tf_issue_id.setBounds(110,15,200,30);
                JTextField tf_return = new JTextField();
                tf_return.setBounds(180,50,130,30);
                JButton return_btn = new JButton("Return");
                return_btn.setBounds(130,170,80,25);
                return_btn.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent event){
                        String issue_id, return_date;
                        issue_id = tf_issue_id.getText();
                        return_date = tf_return.getText();

                        Connection connection = MySQLConnection.connect();

                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            String date1, date2;
                            date2 = return_date;
                            ResultSet rs = statement.executeQuery("SELECT issued_date FROM issued WHERE issue_id = " + issue_id);
                            while ( rs.next() ){
                                date1 = rs.getString(1);
                            }
                            try {
                                Date date_1 = new SimpleDateFormat("dd-MM-yyyy").parse(date1);
                                Date date_2 = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
                                long diff = date_2.getTime() - date_1.getTime();
                                ex.days = (int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            statement.executeUpdate("UPDATE issued SET return_date='" + return_date + "' WHERE issue_id=" + issue_id);
                            g.dispose();
                            Connection connection1 = MySQLConnection.connect();
                            Statement statement1 = connection1.createStatement();
                            statement1.executeUpdate("USE library");
                            ResultSet rs1 = statement1.executeQuery("SELECT period FROM issued WHERE issue_id=" + issue_id);
                            String diff;
                            while ( rs1.next() ){
                                diff = rs1.getString(1);
                            }
                            int diff_int = Integer.parseInt(diff);
                            // Calculate fine
                            // if ( ex.days&amp;gt;diff_int ) {
                            //     int fine = (ex.days-diff_int)*10;
                            //     statement1.executeUpdate("UPDATE issued SET fine = " + fine + "WHERE issue_id = " + issue_id );
                            //     String fine_str = ("Fine: Rs. " + fine);
                            //     JOptionPane.showMessageDialog(null, fine_str);
                            // } 
                            JOptionPane.showMessageDialog(null, "Book returned!");
                        } catch ( SQLException e ) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
                g.add(l2);
                g.add(return_btn);
                g.add(l1);
                g.add(tf_issue_id);
                g.add(tf_return);
                g.setSize(350,250);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
        f.add(create_btn);
        f.add(return_book);
        f.add(issue_book);
        f.add(add_book);
        f.add(issued_btn);
        f.add(users_btn);
        f.add(view_btn);
        f.add(add_user);
        f.setSize(600,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
