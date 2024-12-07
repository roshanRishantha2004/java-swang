package srilankaairways;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register implements ActionListener {
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Register Page1");
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel confirmPassword = new JLabel("Confirm Password");
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JPasswordField confirmPasswordText = new JPasswordField();
    JButton registerButton = new JButton("Register");
    JCheckBox termsCheckBox = new JCheckBox("I agree to the terms and conditions!");
    JLabel termsLabel = new JLabel("Terms and Conditions");
    Register() {
        
        frame.setTitle("Register");
        frame.setSize(800, 500);
        frame.setLayout(null);
        
        label.setBounds(130, 10, 150, 30);
        frame.add(label);
        
        usernameLabel.setBounds(50, 80, 100, 30);
        usernameText.setBounds(150, 80, 180, 30);
        frame.add(usernameLabel);
        frame.add(usernameText);
        
        passwordLabel.setBounds(50, 130, 100, 30);
        passwordText.setBounds(150, 130, 180, 30);
        frame.add(passwordLabel);
        frame.add(passwordText);
        
        confirmPassword.setBounds(50, 180, 150, 30);
        confirmPasswordText.setBounds(150, 180, 180, 30);
        frame.add(confirmPassword);
        frame.add(confirmPasswordText);
        
        termsCheckBox.setBounds(50, 260, 250, 30);
        frame.add(termsCheckBox);
        
        termsLabel.setBounds(150, 230, 300, 30);
        frame.add(termsLabel);
        
        registerButton.setBounds(150, 360, 180, 40);
        frame.add(registerButton);
        
        registerButton.addActionListener(this);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public boolean RegisterFunc(String username, String password) {
        boolean isValidUser = false;
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srilanka-air-ways", "root", "");
             Statement statement = con.createStatement();
             if (con != null) {
                 System.out.println("Connection Success!");
             }
             String query = "INSERT INTO users (username, password) VALUES (?, ?)";
             PreparedStatement pst = con.prepareStatement(query);
             pst.setString(1, username);
             pst.setString(2, password);
             int rs = pst.executeUpdate();
             
             if (rs > 0 ) {
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
         if (e.getSource() == registerButton) {
            String username = usernameText.getText();
            String password = new String(passwordText.getPassword());
            String confirmPassword = new String(confirmPasswordText.getPassword());
                    
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ) {
                
                JOptionPane.showMessageDialog(frame, "All fields must be!");

            } else {
                    if (!termsCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "You must agree to the terms and conditions!");
                    return;
                 }
                 if (!password.equals(confirmPassword)) {
                     JOptionPane.showMessageDialog(frame, "Password do not match!");
                 } 
                 
                 boolean isRegistered = RegisterFunc(username, password);
                 int userId = getUserId(username);
                 
                 if (isRegistered) {
                    JOptionPane.showMessageDialog(frame, "Registraion successful!");
                    Home home = new Home();
                    frame.dispose();
                 }
            }
              
            
         }
    }
}
