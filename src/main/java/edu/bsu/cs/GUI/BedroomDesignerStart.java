package edu.bsu.cs.GUI;/*package edu.bsu.cs;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BedroomDesignerStart extends JFrame {

    public BedroomDesignerStart() {
        // Default constructor
    }
    public void start() {
        JFrame frame = new JFrame("Bedroom Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.getContentPane().setBackground(Color.PINK);

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lengthLabel = new JLabel("Length (feet):");
        JTextField lengthField = new JTextField();

        JLabel widthLabel = new JLabel("Width (feet):");
        JTextField widthField = new JTextField();

        JButton submitButton = new JButton("Submit");

        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(widthLabel);
        frame.add(widthField);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());
                openRoomDesignerGUI(length, width);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    protected void openRoomDesignerGUI(double roomLength, double roomWidth) {
        System.out.println("Opening Room Designer GUI...");
        BedroomDesignerGUI bedroomDesignerGUI = new BedroomDesignerGUI(roomLength, roomWidth);
        bedroomDesignerGUI.setVisible(true);
    }
}*/
import edu.bsu.cs.GUI.BedroomDesignerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BedroomDesignerStart extends JFrame {

    public BedroomDesignerStart() {
        // Default constructor
    }

    public void start() {
        JFrame frame = new JFrame("Bedroom Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.getContentPane().setBackground(Color.PINK);

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lengthLabel = new JLabel("Length (feet):");
        JTextField lengthField = new JTextField();

        JLabel widthLabel = new JLabel("Width (feet):");
        JTextField widthField = new JTextField();

        JButton submitButton = new JButton("Submit");

        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(widthLabel);
        frame.add(widthField);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double length = Double.parseDouble(lengthField.getText());
                    double width = Double.parseDouble(widthField.getText());
                    if (length <= 0 || width <= 0) {
                        showError("Invalid room dimensions. Please enter positive values for length and width.");
                    } else {
                        openRoomDesignerGUI(length, width);
                        frame.dispose();
                    }
                } catch (NumberFormatException ex) {
                    showError("Invalid input format. Please enter numeric values for length and width.");
                }
            }
        });

        frame.setVisible(true);
    }

    protected void openRoomDesignerGUI(double roomLength, double roomWidth) {
        System.out.println("Opening Room Designer GUI...");
        BedroomDesignerGUI bedroomDesignerGUI = new BedroomDesignerGUI(roomLength, roomWidth);
        bedroomDesignerGUI.setVisible(true);
    }

    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}