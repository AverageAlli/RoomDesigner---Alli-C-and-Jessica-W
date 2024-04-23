package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BedroomDesignerGUI extends JFrame {
    private Point lastFurniturePosition = new Point(0, 0);
    private JPanel roomPanel;
    private final double roomLength;
    private final double roomWidth;
    private final Map<String, ImageIcon> furnitureImages;
    private final DragAndDropHandler dragAndDropHandler;
    private final Map<JLabel, Double> rotationAngles = new HashMap<>();


    public BedroomDesignerGUI(double roomLength, double roomWidth) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;

        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        initComponents();
        furnitureImages = loadFurnitureImages();
        dragAndDropHandler = new DragAndDropHandler(roomPanel); // Initialize the DragAndDropHandler
        //rotationAngles = new HashMap<>(); // Initialize rotation angles map

        setVisible(true);
    }

    private void initComponents() {
        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawRoom(g);
            }
        };
        roomPanel.setBackground(Color.WHITE);
        roomPanel.setPreferredSize(new Dimension(400, 400));

        String[] furnitureOptions = {"Select Furniture", "Bed", "Dresser", "Nightstand", "Chair"};
        JComboBox<String> furnitureComboBox = new JComboBox<>(furnitureOptions);
        furnitureComboBox.addActionListener(e -> {
            String selectedFurniture = (String) furnitureComboBox.getSelectedItem();
            if (!Objects.equals(selectedFurniture, "Select Furniture")) {
                displayFurnitureImage(selectedFurniture);
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(furnitureComboBox);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(roomPanel, BorderLayout.CENTER);
    }
    private void rotateFurnitureClockwise(JLabel furnitureLabel) {
        double angle = rotationAngles.getOrDefault(furnitureLabel, 0.0);
        angle += Math.PI / 2; // Increment rotation angle by 90 degrees (PI/2 radians)
        rotationAngles.put(furnitureLabel, angle);

        ImageIcon imageIcon = (ImageIcon) furnitureLabel.getIcon();
        Image image = imageIcon.getImage();
        Image rotatedImage = rotateImageClockwise(image, angle);

        roomPanel.remove(furnitureLabel);

        JLabel rotatedLabel = new JLabel(new ImageIcon(rotatedImage));

        rotatedLabel.setSize(rotatedLabel.getPreferredSize());
        rotatedLabel.setLocation(furnitureLabel.getLocation());
        dragAndDropHandler.makeDraggable(rotatedLabel);



        roomPanel.add(rotatedLabel);


        imageIcon.setImage(rotatedImage);
        furnitureLabel.setIcon(imageIcon);

        roomPanel.revalidate();
        roomPanel.repaint();
//        Component[] components = roomPanel.getComponents();
//        for (Component component : components) {
//            if (component instanceof JLabel) {
//                JLabel furnitureLabel = (JLabel) component;
//                double angle = rotationAngles.getOrDefault(furnitureLabel, 0.0);
//                angle += Math.PI / 2; // Increment rotation angle by 90 degrees (PI/2 radians)
//                rotationAngles.put(furnitureLabel, angle);
//
//                ImageIcon imageIcon = (ImageIcon) furnitureLabel.getIcon();
//                Image image = imageIcon.getImage();
//                Image rotatedImage = rotateImageClockwise(image, angle);
//                imageIcon.setImage(rotatedImage);
//                furnitureLabel.setIcon(imageIcon);
//            }
//        }
//        roomPanel.revalidate();
//        roomPanel.repaint();
    }


    private Map<String, ImageIcon> loadFurnitureImages() {
        Map<String, ImageIcon> images = new HashMap<>();
        try {
            images.put("Bed", new ImageIcon("src/main/ObjectImages/Bed.png"));
            images.put("Dresser", new ImageIcon("src/main/ObjectImages/dresser.png"));
            images.put("Nightstand", new ImageIcon("src/main/ObjectImages/nightstand.png"));
            images.put("Chair", new ImageIcon("src/main/ObjectImages/chair.png"));

        } catch (Exception e) {
            System.err.println("Error loading furniture images: " + e.getMessage());
        }
        return images;
    }

    private void displayFurnitureImage(String furnitureName) {
        ImageIcon imageIcon = furnitureImages.get(furnitureName);
        if (imageIcon != null) {
            JLabel furnitureLabel = new JLabel(imageIcon);
            furnitureLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

            // Enable drag-and-drop support for the furniture label
            dragAndDropHandler.makeDraggable(furnitureLabel);
            furnitureLabel.setToolTipText("Dimensions: " + getFurnitureDimensions(furnitureName));
            furnitureLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rotateFurnitureClockwise(furnitureLabel);
                }
            });
            // Add the furniture label to the room panel
            roomPanel.setLayout(null);
            roomPanel.add(furnitureLabel);
            furnitureLabel.setLocation(lastFurniturePosition);
            // Update the last position of the furniture label
            lastFurniturePosition = new Point(lastFurniturePosition.x + 10, lastFurniturePosition.y + 10);

            roomPanel.revalidate();
            roomPanel.repaint();
        } else {
            System.out.println("Furniture image not found for: " + furnitureName);
        }
    }
    private String getFurnitureDimensions(String furnitureName) {
        return switch (furnitureName) {
            case "Bed" -> "Length: 4ft, Width: 5.5ft";
            case "Dresser" -> "Height: 3ft, Width: 1.5ft";
            case "Nightstand" -> "Height: 2ft, Width: 1.5ft";
            case "Chair" -> "Height: 1.5ft, Width: 1.5ft";
            default -> "";
        };
    }
    private Image rotateImageClockwise(Image img, double angle) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bi = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.rotate(angle, (double) height / 2, (double) height / 2); // Rotate by the specified angle
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bi;
    }

    private void drawRoom(Graphics graphics) {
        int width = roomPanel.getWidth();
        int height = roomPanel.getHeight();

        graphics.clearRect(0, 0, width, height);

        int startX = width / 12;
        int startY = height / 12;
        int roomWidthPixels = (int) (width * roomWidth / 24);
        int roomLengthPixels = (int) (height * roomLength / 24);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(startX, startY, roomWidthPixels, roomLengthPixels);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0, 0));
    }
}
