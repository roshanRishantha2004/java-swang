package srilankaairways;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home {
    
    JFrame frame = new JFrame("Home");
    JLabel label = new JLabel();
    
    JLabel flightDetailsLabel = new JLabel("Flight Details");
    
    JComboBox<String> flightDetailsDropDown;
    
    Home() {
        frame.setTitle("Home");
        frame.setSize(800, 500);
        frame.setLayout(null);
        
//        label.setText("Welcome" + userId);
//        label.setBounds(50, 50, 300, 30);
        frame.add(flightDetailsLabel);
      //  frame.add(label);
        
        String[] flightDetails = { "", "Colombo to Dubai", "Colombo to India" };
        
        flightDetailsDropDown = new JComboBox<>(flightDetails);
        flightDetailsDropDown.setBounds(150, 270, 180, 30);
        frame.add(flightDetailsDropDown);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
