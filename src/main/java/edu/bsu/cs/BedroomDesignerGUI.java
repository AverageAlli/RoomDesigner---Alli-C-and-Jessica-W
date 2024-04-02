package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    private int roomSize;
    private JLabel furnitureLabel;
    private Map<String, ImageIcon> furnitureImages;

    public BedroomDesignerGUI(int roomSize) {
        this.roomSize = roomSize;
        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        initComponents();
        furnitureImages = loadFurnitureImages();

        setVisible(true);
    }

    private void initComponents() {
        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawRoom(g); // Draw the room outline
            }
        };
        roomPanel.setBackground(Color.WHITE);
        roomPanel.setPreferredSize(new Dimension(400, 400));

        // Add drag and drop functionality to the roomPanel
        new DragAndDropHandler(roomPanel);

        String[] furnitureOptions = {"Select Furniture", "Bed", "Dresser", "Nightstand", "Chair"};
        JComboBox<String> furnitureComboBox = new JComboBox<>(furnitureOptions);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(furnitureComboBox);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(roomPanel, BorderLayout.CENTER);
    }
    private Map<String, ImageIcon> loadFurnitureImages() {
        Map<String, ImageIcon> images = new HashMap<>();
        // Load images for each furniture type and store in the map
        images.put("Bed",new ImageIcon("ObjectImages/bed.png"));
        // Example: images.put("Bed", new ImageIcon("bed.png"));
        return images;
    }
    private void displayFurnitureImage(String furnitureName) {
        ImageIcon image = furnitureImages.get(furnitureName);
        if (image != null) {
            furnitureLabel.setIcon(image);
            furnitureLabel.setText("");
        }
    }

    private void removeFurnitureImage() {
        furnitureLabel.setIcon(null);
        furnitureLabel.setText("No furniture selected");
    }
    private void drawRoom(Graphics graphics) {
        int width = roomPanel.getWidth();
        int height = roomPanel.getHeight();

        // Clear the panel before drawing
        graphics.clearRect(0, 0, width, height);

        // Draw the outline of the room based on the selected room size
        graphics.setColor(Color.BLACK);
        switch (roomSize) {
            case 1: // 9x16
                graphics.drawRect(width / 12, height / 12, width * 4 / 6, height * 4 / 6);
                break;
            case 2: // 10x8
                graphics.drawRect(width / 8, height / 8, width * 3 / 4, height * 3 / 4);
                break;
            case 3: // 10x12
                graphics.drawRect(width / 8, height / 6, width * 3 / 4, height * 4 / 6);
                break;
            case 4: // 10x14
                graphics.drawRect(width / 8, height / 6, width * 3 / 4, height * 5 / 6);
                break;
            case 5: // 12x8
                graphics.drawRect(width / 12, height / 4, width * 4 / 6, height / 2);
                break;
            case 6: // 12x12
                graphics.drawRect(width / 12, height / 12, width * 4 / 6, height * 4 / 6);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0)); // Placeholder value for room size
    }
}