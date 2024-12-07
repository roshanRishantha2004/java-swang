
package srilankaairways;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookingTicket implements ActionListener{
    
    JFrame frame = new JFrame();
        JLabel bannerLabel = new JLabel();

    JLabel firstNameLabel = new JLabel("First Name");
    JLabel lastNameLabel = new JLabel("Last Name");
    JLabel passportNumberLabel = new JLabel("Passport Number");
    JLabel nationalityLabel = new JLabel("Nationality");
    JLabel destinationLabel = new JLabel("Destination");
    JLabel depautureLabel = new JLabel("Depauture");
    JLabel arrivalLabel = new JLabel("Arrival");
    JLabel dateLabel = new JLabel("Date");
    JLabel flightNumber = new JLabel("Flight Number");
    JLabel ticketTypeLabel = new JLabel("Ticket Type");
    JLabel amountLabel = new JLabel("Amount");
    JLabel amountdisplay = new JLabel();
    
    JTextField firstNameText = new JTextField();
    JTextField lastNameText = new JTextField();
    JTextField passportNumberText = new JTextField();
    JTextField nationalityText = new JTextField();
    JTextField depautureText = new JTextField();
    JTextField arrivalText = new JTextField();
    JTextField dateText = new JTextField();
    JTextField flightNumberText = new JTextField();
    JComboBox<String> ticketTypeText = new JComboBox<>(new String[]{"Economy", "Busiines"});
    
    JButton bookButton = new JButton("Book");
    JButton gobackButton = new JButton("Back");
    
    
    BookingTicket(){
      frame.setTitle("Booking");
      frame.setSize(800, 500);
      frame.setLayout(null);
      
      ImageIcon bannerIcon = new ImageIcon("R.jpeg");
      bannerLabel.setIcon(bannerIcon);
      bannerLabel.setBounds(0, 0, 800, 100);
      frame.add(bannerLabel);
       
      firstNameLabel.setBounds(50, 30, 100, 25);
      firstNameText.setBounds(150, 30, 150, 25);
      frame.add(firstNameLabel);
      frame.add(firstNameText);
      
      lastNameLabel.setBounds(50, 70, 100, 25);
      lastNameText.setBounds(150, 70, 150, 25);
      frame.add(lastNameLabel);
      frame.add(lastNameText);
      
      passportNumberLabel.setBounds(50, 110, 150, 25);
      passportNumberText.setBounds(150, 110, 150, 25);
      frame.add(passportNumberLabel);
      frame.add(passportNumberText);
      
      nationalityLabel.setBounds(50, 150, 100, 25);
      nationalityText.setBounds(150, 150, 150, 25);
      frame.add(nationalityLabel);
      frame.add(nationalityText);
      
      destinationLabel.setBounds(50, 100, 100, 25);
      frame.add(destinationLabel);
      
      depautureLabel.setBounds(50, 230, 100, 25);
      depautureText.setBounds(150, 230, 150, 25);
      frame.add(depautureLabel);
      frame.add(depautureText);
      
      arrivalLabel.setBounds(50, 270, 100, 25);
      arrivalText.setBounds(150, 270, 150, 25);
      frame.add(arrivalLabel);
      frame.add(arrivalText);
      
      dateLabel.setBounds(50, 310,  100, 25);
      dateText.setBounds(150, 310, 150, 25);
      frame.add(dateLabel);
      frame.add(dateText);
      
      flightNumber.setBounds(50, 350, 100, 25);
      flightNumberText.setBounds(150, 350, 150, 25);
      frame.add(flightNumber);
      frame.add(flightNumberText);
      
      ticketTypeLabel.setBounds(50, 390, 100, 25);
      ticketTypeText.setBounds(150, 390, 150, 25);
      frame.add(ticketTypeLabel);
      frame.add(ticketTypeText);
      
      amountLabel.setBounds(50, 430, 100, 25);
      amountdisplay.setBounds(150, 430, 150, 25);
      frame.add(amountLabel);
      frame.add(amountdisplay);
      
      bookButton.setBounds(50, 500, 100, 25);
      gobackButton.setBounds(150, 500, 150, 25);
     frame.add(bookButton);
     frame.add(gobackButton);
      
     
     bookButton.addActionListener(this);
     gobackButton.addActionListener(this);
      
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    public boolean BookingFunc(int userId, String firstName,  String lastName, String passportNumber, String nationality, String depauture, String arival, String bookingDate, String flightNumber, String ticketType,  int amount ) {
        boolean isBook = false;
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/srilanka-air-ways", "root", "");
             Statement statement = con.createStatement();
             if (con != null) {
                 System.out.println("Connection Success!");
             }
             String query = "INSERT INTO bookings (userId, firstName, lastName, passportNumber, nationality, depauture, arival, bookingDate, flightNumber, ticketType, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement pst = con.prepareStatement(query);
             pst.setInt(1, userId);
             pst.setString(2, firstName);
             pst.setString(3, lastName);
             pst.setString(4, passportNumber);
             pst.setString(5, nationality);
             pst.setString(6, depauture);
             pst.setString(7, arival);
             pst.setString(8, bookingDate);
                          pst.setString(9, flightNumber);

             pst.setString(10, ticketType);
             pst.setInt(11, amount);






             int rs = pst.executeUpdate();
             
             if (rs > 0 ) {
                 isBook = true;
             }
         } catch (SQLException ex) {
             System.out.println("Error: " + ex);
         }
        
        return isBook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == gobackButton) {
            Home home = new Home();
            frame.dispose();
        }
        
        if (e.getSource() == bookButton) {
            
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String passportNumber = passportNumberText.getText();
            String nationality = nationalityText.getText();
            String depauture = depautureText.getText();
            String arival = arrivalText.getText();
            String date = dateText.getText();
            String flightNumber = flightNumberText.getText();
           // String ticketType = ticketTypeText.getText();
           String ticketType = (String) ticketTypeText.getSelectedItem();
           System.out.println(ticketType);
           int amount;
           if (ticketType.equals("Economy")) {
               amount = 500;
               amountdisplay.setText("$500"); 

           } else {
               amount = 1000;
              amountdisplay.setText("$1000"); 

           }
           int userId = 1;
            boolean isBook = BookingFunc(userId, firstName, lastName, passportNumber, nationality, depauture, arival, date, flightNumber, ticketType, amount );
            
            if (!isBook) {
                JOptionPane.showMessageDialog(frame, "Can not create a booking, please try agin later!");
            } else {
                JOptionPane.showMessageDialog(frame, "Booked!");
                Home home = new Home();
                frame.dispose();
            }
            
            
            
        }
    }
}
