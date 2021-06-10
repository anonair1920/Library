package newpackage;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import newpackage.Main.ex;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_menu {
    public static void admin_menu() {
        JFrame f = new JFrame("Admin Functions");

        // Reset Db
        JButton create_btn = new JButton("Reset Database");
        create_btn.setBounds(450, 60, 120, 25);
        create_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Create.create();
                JOptionPane.showMessageDialog(null, "New Database Created!");
            }
        });

        // View all books
        JButton view_btn = new JButton("View Lirary");
        view_btn.setBounds(20, 20, 120, 25);
        view_btn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent event) { 
                JFrame f = new JFrame("Books Available");
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM books";

                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Book ID");
                    model.addColumn("Title");
                    model.addColumn("Author");
                    model.addColumn("Published");
                    while (rs.next()) {
                        model.addRow(
                                new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
                    }
                    JTable book_list = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(book_list);
  
                    f.add(scrollPane);
                    f.setSize(1200, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        // View all users
        JButton users_btn = new JButton("View Users");
        users_btn.setBounds(20, 60, 120, 25);
        users_btn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("Users List");
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT * FROM users";
   
                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("User ID");
                    model.addColumn("Name");
                    model.addColumn("Email");
   
                    while (rs.next()) {
                        model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });
                    }
                    JTable users_list = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(users_list);

                    f.add(scrollPane);
                    f.setSize(800, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
     
        // View all issue books
        JButton issued_btn = new JButton("View Issued Books");
        issued_btn.setBounds(280, 60, 160, 25);
        issued_btn.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent event) {
                JFrame f = new JFrame("Issued Books");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Connection connection = MySQLConnection.connect();
                String sql = "SELECT DISTINCT issued.*, users.name, books.title FROM issued, users, books WHERE ((issued.user_id = users.id) AND (issued.book_id = books.id));";

                try {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("USE library");
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Issue ID"); //1
                    model.addColumn("User Name"); //7
                    model.addColumn("Book Title"); //8
                    model.addColumn("Issued Date"); //4
                    model.addColumn("Period"); //5
                    model.addColumn("Return Date"); //6
               
                    while (rs.next()) {
                        model.addRow(new Object[] { rs.getString(1), rs.getString(7), rs.getString(8),rs.getString(4), rs.getString(5), rs.getString(6)});
                    }
                    JTable book_list = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(book_list);
                
                    f.add(scrollPane);
                    f.setSize(1200, 400);
                    f.setVisible(true);
                    f.setLocationRelativeTo(null);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
   
        // Add user
        JButton add_user = new JButton("Add User");
        add_user.setBounds(150, 60, 120, 25);
        add_user.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent event) {
                JFrame g = new JFrame("Enter User Details");
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1, l2;
                l1 = new JLabel("Username");
                l1.setBounds(30, 15, 100, 30);
                l2 = new JLabel("Password");
                l2.setBounds(30, 50, 100, 30);

                JTextField tf_user = new JTextField();
                tf_user.setBounds(110, 15, 200, 30);
                JTextField tf_password = new JTextField();
                tf_password.setBounds(110, 50, 200, 30);
                JRadioButton u1 = new JRadioButton("Admin");
                u1.setBounds(55, 80, 200, 30);
                JRadioButton u2 = new JRadioButton("User");
                u2.setBounds(130, 80, 200, 30);
                ButtonGroup btngr = new ButtonGroup();
                btngr.add(u1);
                btngr.add(u2);
                JButton create_btn = new JButton("CREATE");
                create_btn.setBounds(130, 130, 80, 25);
                create_btn.addActionListener(new ActionListener() {
              
                    public void actionPerformed(ActionEvent event) {
                        String username = tf_user.getText();
                        String password = tf_password.getText();
                        Boolean admin = false;
                        if (u1.isSelected()) {
                            admin = true;
                        }
                        Connection connection = MySQLConnection.connect();
                      
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library;");
                            statement.executeUpdate("INSERT INTO users(name, password, admin) VALUES('" + username
                                    + "','" + password + "'," + admin + ");");
                            JOptionPane.showMessageDialog(null, "User added!");
                            System.out.println("Added new user!");
                            g.dispose();
                        } catch (SQLException e) {
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
                g.setSize(350, 200);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
    
        // Add Books
        JButton add_book = new JButton("Add Book");
        add_book.setBounds(150, 20, 120, 25);
        add_book.addActionListener(new ActionListener() {
  
            public void actionPerformed(ActionEvent event) {
                JFrame g = new JFrame("Enter Book Details");
                JLabel l1, l2, l3;
                l1 = new JLabel("Title");
                l1.setBounds(30, 15, 100, 30);
                l2 = new JLabel("Author");
                l2.setBounds(30, 53, 100, 30);
                l3 = new JLabel("Published");
                l3.setBounds(30, 90, 100, 30);
                JTextField tf_title = new JTextField();
                tf_title.setBounds(110, 15, 200, 30);
                JTextField tf_author = new JTextField();
                tf_author.setBounds(110, 53, 200, 30);
                JTextField tf_published = new JTextField();
                tf_published.setBounds(110, 90, 200, 30);
                JButton create_btn = new JButton("Submit");
                create_btn.setBounds(130, 130, 80, 25);
                create_btn.addActionListener(new ActionListener() {
 
                    public void actionPerformed(ActionEvent event) {
                        String title, author, published;
                        title = tf_title.getText();
                        author = tf_author.getText();
                        published = tf_published.getText();
                        // int published_int = Integer.parseInt(published);
                        System.out.println(published);
                        Connection connection = MySQLConnection.connect();
                    
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            statement.executeUpdate("INSERT INTO books(title, author, published) VALUES('" + title
                                    + "','" + author + "','" + published + "');");
                            JOptionPane.showMessageDialog(null, "Book added!");
                            System.out.println("Added new book!");
                            g.dispose();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
               
                g.add(l3);
                g.add(create_btn);
                g.add(l1);
                g.add(l2);
                g.add(tf_title);
                g.add(tf_author);
                g.add(tf_published);
                g.setSize(350, 200);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
    
        // Issue a book
        JButton issue_book = new JButton("Issue Book");
        issue_book.setBounds(280, 20, 160, 25);
        issue_book.addActionListener(new ActionListener() {
       
            public void actionPerformed(ActionEvent event) {
                JFrame g = new JFrame("Enter Details");
                // g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1, l2, l3, l4;
                l1 = new JLabel("Book ID");
                l1.setBounds(30, 15, 100, 30);
                l2 = new JLabel("User ID");
                l2.setBounds(30, 53, 100, 30);
                l3 = new JLabel("Period(days)");
                l3.setBounds(30, 90, 100, 30);
                l4 = new JLabel("Issued Date");
                l4.setBounds(30, 127, 150, 30);
                JTextField tf_book_id, tf_user_id, tf_period_date, tf_issued_date;
                tf_book_id = new JTextField();
                tf_book_id.setBounds(110, 15, 200, 30);
                tf_user_id = new JTextField();
                tf_user_id.setBounds(110, 53, 200, 30);
                tf_period_date = new JTextField();
                tf_period_date.setBounds(110, 90, 200, 30);
                tf_issued_date = new JTextField();
                tf_issued_date.setBounds(180, 130, 130, 30);
                JButton create_btn = new JButton("Submit");
                create_btn.setBounds(130, 170, 80, 25);
                create_btn.addActionListener(new ActionListener() {
               
                    public void actionPerformed(ActionEvent event) {
                        String book_id, user_id, period, issued_date;
                        book_id = tf_book_id.getText();
                        user_id = tf_user_id.getText();
                        period = tf_period_date.getText();
                        issued_date = tf_issued_date.getText();
                        int period_int = Integer.parseInt(period);
                        Connection connection = MySQLConnection.connect();
                      
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library;");
                            statement.executeUpdate("INSERT INTO issued(user_id, book_id, issued_date, period) VALUES('"
                                    + user_id + "','" + book_id + "','" + issued_date + "'," + period_int + ");");
                            JOptionPane.showMessageDialog(null, "Book Issued!");
                            System.out.println("New Issue added!");
                            g.dispose();
                        } catch (SQLException e) {
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
                g.setSize(350, 250);
                g.setLayout(null);
                g.setVisible(true);
                g.setLocationRelativeTo(null);
            }
        });
      
        // Return a book
        JButton return_book = new JButton("Return Book");
        return_book.setBounds(450, 20, 120, 25);
        return_book.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent event) {
                JFrame g = new JFrame("Enter Details");
                JLabel l1, l2;
                l1 = new JLabel("Issue ID");
                l1.setBounds(30, 15, 100, 30);
                l2 = new JLabel("Return Date");
                l2.setBounds(30, 50, 150, 30);
                JTextField tf_issue_id = new JTextField();
                tf_issue_id.setBounds(110, 15, 200, 30);
                JTextField tf_return = new JTextField();
                tf_return.setBounds(180, 50, 130, 30);
                JButton return_btn = new JButton("Return");
                return_btn.setBounds(130, 170, 80, 25);
                return_btn.addActionListener(new ActionListener() {
                 
                    public void actionPerformed(ActionEvent event) {
                        String issue_id, return_date;
                        issue_id = tf_issue_id.getText();
                        return_date = tf_return.getText();

                        Connection connection = MySQLConnection.connect();

                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("USE library");
                            String date1 = "";
                            String date2 = return_date;
                            ResultSet rs = statement
                                    .executeQuery("SELECT issued_date FROM issued WHERE issue_id = " + issue_id);
                            while (rs.next()) {
                                date1 = rs.getString(1);
                            }
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate d1 = LocalDate.parse(date1, dtf);
                            LocalDate d2 = LocalDate.parse(date2, dtf);
                            long dif = Duration.between(d1.atStartOfDay(), d2.atStartOfDay()).toDays();
                            System.out.println(dif);
                            ex.days = (int) (TimeUnit.DAYS.convert(dif, TimeUnit.DAYS));
                            statement.executeUpdate("UPDATE issued SET return_date='" + return_date
                                    + "' WHERE issue_id=" + issue_id + ";");
                            g.dispose();
                            Connection connection1 = MySQLConnection.connect();
                            Statement statement1 = connection1.createStatement();
                            statement1.executeUpdate("USE library");
                            ResultSet rs1 = statement1
                                    .executeQuery("SELECT period FROM issued WHERE issue_id=" + issue_id + ";");
                            String period;
                            while (rs1.next()) {
                                period = rs1.getString(1);
                                int period_int = Integer.parseInt(period);
                                int dif_int = (int) dif;
                                if (period_int < dif_int) {
                                    int fine = (dif_int - period_int) * 2;
                                    JOptionPane.showMessageDialog(null, "Fine:   $" + fine);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Book returned!");
                                }
                            }
                            // int diff_int = Integer.parseInt(diff);
                            // Calculate fine
                            // if ( ex.days&amp;gt;diff_int ) {
                            // int fine = (ex.days-diff_int)*10;
                            // statement1.executeUpdate("UPDATE issued SET fine = " + fine + "WHERE issue_id
                            // = " + issue_id );
                            // String fine_str = ("Fine: " + fine);
                            // JOptionPane.showMessageDialog(null, fine_str);
                            // }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
              
                g.add(l2);
                g.add(return_btn);
                g.add(l1);
                g.add(tf_issue_id);
                g.add(tf_return);
                g.setSize(350, 250);
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
        f.setSize(600, 200);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
