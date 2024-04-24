package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
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
        dragAndDropHandler = new DragAndDropHandler(roomPanel);

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

        JButton clearButton = new JButton("Clear Room");
        clearButton.addActionListener(e -> clearRoom());

        JButton addDoorButton = new JButton("Add Door");
        addDoorButton.addActionListener(e -> addDoorImage());

        JButton addWindowButton = new JButton("Add Window");
        addWindowButton.addActionListener(e -> addWindowImage());

        String[] furnitureOptions = {"Select Furniture", "Bed", "Dresser", "Nightstand", "Chair", "Desk", "Shelf"};
        JComboBox<String> furnitureComboBox = new JComboBox<>(furnitureOptions);
        furnitureComboBox.addActionListener(e -> {
            String selectedFurniture = (String) furnitureComboBox.getSelectedItem();
            if (!Objects.equals(selectedFurniture, "Select Furniture")) {
                displayFurnitureImage(selectedFurniture);
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(furnitureComboBox);
        controlPanel.add(clearButton);
        controlPanel.add(addDoorButton);
        controlPanel.add(addWindowButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(roomPanel, BorderLayout.CENTER);
    }
    private void addDoorImage() {
        ImageIcon doorIcon = new ImageIcon("src/main/ObjectImages/door.png");
        JLabel doorLabel = new JLabel(doorIcon);
        doorLabel.setSize(doorIcon.getIconWidth(), doorIcon.getIconHeight());

        dragAndDropHandler.makeDraggable(doorLabel);

        doorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rotateFurnitureClockwise(doorLabel);
            }
        });
        roomPanel.add(doorLabel);

        roomPanel.revalidate();
        roomPanel.repaint();
    }
    private void addWindowImage() {
        ImageIcon windowIcon = new ImageIcon("src/main/ObjectImages/window.png");
        JLabel windowLabel = new JLabel(windowIcon);
        windowLabel.setSize(windowIcon.getIconWidth(), windowIcon.getIconHeight());

        dragAndDropHandler.makeDraggable(windowLabel);
        windowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rotateFurnitureClockwise(windowLabel);
            }
        });
        roomPanel.add(windowLabel);
        roomPanel.revalidate();
        roomPanel.repaint();
    }
    private void clearRoom() {
        roomPanel.removeAll();
        lastFurniturePosition = new Point(0, 0);
        roomPanel.revalidate();
        roomPanel.repaint();
    }
    private void rotateFurnitureClockwise(JLabel furnitureLabel) {
        double angle = rotationAngles.getOrDefault(furnitureLabel, 0.0);
        angle += Math.PI / 2;
        rotationAngles.put(furnitureLabel, angle);

        ImageIcon imageIcon = (ImageIcon) furnitureLabel.getIcon();
        Image image = imageIcon.getImage();
        Image rotatedImage = rotateImageClockwise(image, angle);

        furnitureLabel.setIcon(new ImageIcon(rotatedImage));
        adjustFurniturePositionAfterRotation(furnitureLabel, angle);

        roomPanel.revalidate();
        roomPanel.repaint();
    }
    private void adjustFurniturePositionAfterRotation(JLabel furnitureLabel, double angle) {
        int labelWidth = furnitureLabel.getWidth();
        int labelHeight = furnitureLabel.getHeight();

        int newX = (int) (furnitureLabel.getX() + (labelWidth - labelHeight) / 2);
        int newY = (int) (furnitureLabel.getY() + (labelHeight - labelWidth) / 2);

        furnitureLabel.setLocation(newX, newY);
    }

    private Map<String, ImageIcon> loadFurnitureImages() {
        Map<String, ImageIcon> images = new HashMap<>();
        try {
            images.put("Bed", new ImageIcon("src/main/ObjectImages/Bed.png"));
            images.put("Dresser", new ImageIcon("src/main/ObjectImages/dresser.png"));
            images.put("Nightstand", new ImageIcon("src/main/ObjectImages/nightstand.png"));
            images.put("Chair", new ImageIcon("src/main/ObjectImages/chair.png"));
            images.put("Desk", new ImageIcon("src/main/ObjectImages/desk.png"));
            images.put("Shelf", new ImageIcon("src/main/ObjectImages/shelf.png"));
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
            dragAndDropHandler.makeDraggable(furnitureLabel);
            furnitureLabel.setToolTipText("Dimensions: " + getFurnitureDimensions(furnitureName));
            furnitureLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rotateFurnitureClockwise(furnitureLabel);
                }
            });
            roomPanel.setLayout(null);
            roomPanel.add(furnitureLabel);
            furnitureLabel.setLocation(lastFurniturePosition);
            lastFurniturePosition = new Point(lastFurniturePosition.x + 50, lastFurniturePosition.y + 50);
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
            case "Shelf" -> "Height: , Width: ";
            case "Desk" -> "Height: , Width: ";

            default -> "";
        };
    }
    private Image rotateImageClockwise(Image img, double angle) {

        int width = img.getWidth(null);
        int height = img.getHeight(null);

        int newWidth = (int) Math.ceil(width * Math.abs(Math.cos(angle)) + height * Math.abs(Math.sin(angle)));
        int newHeight = (int) Math.ceil(width * Math.abs(Math.sin(angle)) + height * Math.abs(Math.cos(angle)));

        BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();

        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(angle, (double) width / 2, (double) height / 2); // Rotate by the specified angle
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
