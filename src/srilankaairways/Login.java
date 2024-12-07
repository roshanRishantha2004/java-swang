package srilankaairways;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.ImageIcon;

public class Login implements ActionListener{
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Welcome to Sri Lanka Airways!");
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    ImageIcon bgImg = new ImageIcon("bg.jpg");
    JLabel backgorundLabel = new JLabel("", bgImg, JLabel.CENTER);
    Login(){
        
        frame.setTitle("Login");
        frame.setSize(800, 580);
        frame.setLayout(null);
        
        backgorundLabel.setBounds(0, 0, 800, 500);
        frame.add(backgorundLabel);
        
        label.setBounds(100, 130, 250, 30);
        frame.add(label);
        
        usernameLabel.setBounds(50, 170, 100, 30);
        usernameText.setBounds(150, 170, 180, 30);
        frame.add(usernameLabel);
        frame.add(usernameText);
        
        passwordLabel.setBounds(50, 210, 100, 30);
        passwordText.setBounds(150, 210, 180, 30);
        frame.add(passwordLabel);
        frame.add(passwordText);
        
        loginButton.setBounds(50, 260, 120, 40);
        frame.add(loginButton);
        
        registerButton.setBounds(50, 300, 120, 40);
        frame.add(registerButton);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        
        frame.setVisible(true);

    }
    
    public boolean LoginFunc(String username, String password) {
        boolean isValidUser = false;
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srilanka-air-ways", "root", "");
             Statement statement = con.createStatement();
             if (con != null) {
                 System.out.println("Connection Success!");
             }
             String query = "SELECT * FROM users WHERE username = ? AND password = ?";
             PreparedStatement pst = con.prepareStatement(query);
             pst.setString(1, username);
             pst.setString(2, password);
             ResultSet rs = pst.executeQuery();
             
             if (rs.next()) {
                 isValidUser = true;
             }
         } catch (SQLException ex) {
             System.out.println("Error: " + ex);
         }
        
        return isValidUser;
    }
    
    
    public int getUserId(String username) {
        int userId = -1;
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srilanka-air-ways", "root", "");
             Statement statement = con.createStatement();
             if (con != null) {
                 System.out.println("Connection Success!");
             }
             String query = "SELECT id FROM users WHERE username = ?";
             PreparedStatement pst = con.prepareStatement(query);
             pst.setString(1, username);
             ResultSet rs = pst.executeQuery();
             
             if (rs.next()) {
                 userId = rs.getInt("id");
             }
         } catch (SQLException ex) {
             System.out.println("Error: " + ex);
         }
        
        return userId;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == loginButton){
         String username = usernameText.getText();
         String password = passwordText.getText();
         
         boolean response = LoginFunc(username, password);
         int userId = getUserId(username);
         
         if (response) {
             JOptionPane.showMessageDialog(frame, "Login Successful!");
             Home home = new Home();
             frame.dispose();
         } else {
             JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again!");
         }
        }
        if(e.getSource() == registerButton){
            Register register = new Register();
            frame.dispose();
        }
    }
    
}
