package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;

    public BedroomDesignerGUI(int roomSize) {
        setTitle("Bedroom Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BorderLayout());

        // Load the background image based on the room size option
        ImageIcon backgroundImage = getBackgroundImage(roomSize);

        // Create a panel to display the background image
        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                roomPanel.setBackground(Color.GREEN);
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 50, 50, getWidth(), getHeight(), this);
            }
        };

        add(roomPanel, BorderLayout.CENTER);

        // Create a combo box for furniture selection
        String[] furnitureOptions = {"Select Furniture", "Bed", "Dresser", "Nightstand", "Chair"};
        JComboBox<String> furnitureComboBox = new JComboBox<>(furnitureOptions);
        furnitureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle furniture selection
                String selectedItem = (String) furnitureComboBox.getSelectedItem();
                if (!selectedItem.equals("Select Furniture")) {
                    ImageIcon furnitureImage = getFurnitureImage(selectedItem);
                    // Set up drag-and-drop functionality for the selected furniture image
                    DragAndDropHandler dragAndDropHandler = new DragAndDropHandler(roomPanel, furnitureImage);
                }
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(furnitureComboBox);

        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    // Method to load background image based on room size option
    private ImageIcon getBackgroundImage(int roomSize) {
        String imagePath = getImagePathForRoomSize(roomSize);
        if (imagePath != null) {
            return new ImageIcon(imagePath);
        } else {
            System.err.println("Invalid room size option. Using default.");
            return null;
        }
    }

    // Method to load furniture image based on selection
    private ImageIcon getFurnitureImage(String furniture) {
        String imagePath = "src/main/furniture/" + furniture.toLowerCase() + ".png";
        return new ImageIcon(imagePath);
    }

    // Method to get the image path based on the room size option
    private String getImagePathForRoomSize(int roomSize) {
        String directoryPath = "src/main/roomLayouts/";
        switch (roomSize) {
            case 1:
                return directoryPath + "room9x16.png";
            case 2:
                return directoryPath + "room10x8.png";
            case 3:
                return directoryPath + "room10x12.png";
            case 4:
                return directoryPath + "room10x14.png";
            case 5:
                return directoryPath + "room12x8.png";
            case 6:
                return directoryPath + "room12x12.png";
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(1)); // Default room size option
    }
}