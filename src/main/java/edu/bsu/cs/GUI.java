package edu.bsu.cs;

import javax.swing.*;
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

    public static void main(String[] args) {
        RoomMaker roomMaker = new RoomMaker();
        SwingUtilities.invokeLater(() -> new GUI(roomMaker));
    }
}

