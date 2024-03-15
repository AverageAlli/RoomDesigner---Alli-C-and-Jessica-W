package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JComboBox<String> itemsDropdown;
    private JLabel selectedItemLabel;
    private RoomMaker roomMaker; // Add reference to RoomMaker

    private static final String[] items = {"TwinBed", "KingBed", "QueenBed", "Desk", "LongDresser", "TallDresser", "NightStand"};

    public GUI(RoomMaker roomMaker) {
        super("Name Dropdown GUI");
        this.roomMaker = roomMaker; // Initialize RoomMaker reference
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        itemsDropdown = new JComboBox<>(items);
        itemsDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItems = (String) itemsDropdown.getSelectedItem();
                selectedItemLabel.setText("Selected Item: " + selectedItems);
            }
        });

        selectedItemLabel = new JLabel("Selected Item: ");

        JPanel panel = new JPanel();
        panel.add(itemsDropdown);
        panel.add(selectedItemLabel);

        add(panel);

        setVisible(true);
    }

}

/*public class GUI extends JFrame {
    private JTextField lengthField;
    private JTextField widthField;
    private JButton createButton;

    public GUI() {
        super("Room Dimensions Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Create text fields for length and width
        lengthField = new JTextField(10);
        widthField = new JTextField(10);

        // Create a button to create the room
        createButton = new JButton("Create Room");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the length and width values from the text fields
                double length = Double.parseDouble(lengthField.getText());
                double width = Double.parseDouble(widthField.getText());

                // Pass the length and width to the RoomMaker class
                RoomMaker roomMaker = new RoomMaker(length, width);
            }
        });

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(new JLabel("Enter length:"));
        panel.add(lengthField);
        panel.add(new JLabel("Enter width:"));
        panel.add(widthField);
        panel.add(createButton);

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }
}*/
