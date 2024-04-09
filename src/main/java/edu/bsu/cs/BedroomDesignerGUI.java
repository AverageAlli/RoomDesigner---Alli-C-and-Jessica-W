/*package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    private double roomLength;
    private double roomWidth;
    private Map<String, ImageIcon> ObjectImages;

    public BedroomDesignerGUI(double roomLength, double roomWidth) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;

        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        initComponents();
        ObjectImages = loadFurnitureImages();

        setVisible(true);
    }

    private void initComponents() {
        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                System.out.println("Panel size: " + roomPanel.getWidth() + "x" + roomPanel.getHeight()); // Debug print statement
                RoomDrawer.drawRoom(g, roomPanel.getWidth(), roomPanel.getHeight(), roomLength, roomWidth);
            }
        };
        roomPanel.setBackground(Color.WHITE);
        roomPanel.setPreferredSize(new Dimension(1000, 800));
        roomPanel.repaint();

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
        try {
            // Load images for each furniture type and store in the map
            images.put("Bed", new ImageIcon("ObjectImages/bed.png"));
            // Add more image loading code for other furniture types if needed
        } catch (Exception e) {
            // Handle any exceptions that occur during image loading
            e.printStackTrace();
        }
        return images;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0, 0)); // Placeholder value for room size
    }
}*/
package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    private double roomLength;
    private double roomWidth;
    private Map<String, ImageIcon> furnitureImages;

    public BedroomDesignerGUI(double roomLength, double roomWidth) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;

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
        // For example:
        // images.put("Bed", new ImageIcon("ObjectImages/bed.png"));
        return images;
    }

    private void drawRoom(Graphics graphics) {
        int width = roomPanel.getWidth();
        int height = roomPanel.getHeight();

        // Clear the panel before drawing
        graphics.clearRect(0, 0, width, height);

        // Draw the outline of the room based on the room size
        int startX = width / 12;
        int startY = height / 12;
        int roomWidthPixels = (int) (width * roomWidth / 12);
        int roomLengthPixels = (int) (height * roomLength / 12);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(startX, startY, roomWidthPixels, roomLengthPixels);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0, 0)); // Placeholder values for room dimensions
    }
}