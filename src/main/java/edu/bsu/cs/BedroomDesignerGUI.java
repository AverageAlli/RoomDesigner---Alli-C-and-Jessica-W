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

        JButton enterNewSizeButton = new JButton("Enter New Room Size"); // New button added
        enterNewSizeButton.addActionListener(e -> enterNewRoomSize());

        String[] furnitureOptions = {"Select Furniture", "Queen Bed", "Dresser", "Nightstand", "Chair", "Desk", "Shelf"};
        JComboBox<String> furnitureComboBox = new JComboBox<>(furnitureOptions);
        furnitureComboBox.addActionListener(e -> {
            String selectedFurniture = (String) furnitureComboBox.getSelectedItem();
            if (!Objects.equals(selectedFurniture, "Select Furniture")) {
                displayFurnitureImage(selectedFurniture);
            }
        });
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(furnitureComboBox);
        controlPanel.add(clearButton);
        controlPanel.add(addDoorButton);
        controlPanel.add(addWindowButton);
        controlPanel.add(enterNewSizeButton);

        JLabel titleLabel = new JLabel("Room Designer");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel messageLabel = new JLabel("Drag and drop furniture to design your room. To delete a single piece of furniture, right click on it. To see the dimensions of the furniture, hover over the item.");
        messageLabel.setFont(messageLabel.getFont().deriveFont(Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(messageLabel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
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

        int newWidth = rotatedImage.getWidth(null);
        int newHeight = rotatedImage.getHeight(null);
        furnitureLabel.setSize(newWidth, newHeight);

        furnitureLabel.setIcon(new ImageIcon(rotatedImage));
        adjustFurniturePositionAfterRotation(furnitureLabel, angle);

        roomPanel.revalidate();
        roomPanel.repaint();
    }
    private void adjustFurniturePositionAfterRotation(JLabel furnitureLabel, double ignoredAngle) {
        int labelWidth = furnitureLabel.getWidth();
        int labelHeight = furnitureLabel.getHeight();

        int newX = furnitureLabel.getX() + (labelWidth - labelHeight) / 2;
        int newY = furnitureLabel.getY() + (labelHeight - labelWidth) / 2;

        furnitureLabel.setLocation(newX, newY);
    }

    private Map<String, ImageIcon> loadFurnitureImages() {
        Map<String, ImageIcon> images = new HashMap<>();
        try {
            images.put("Queen Bed", new ImageIcon("src/main/ObjectImages/Bed.png"));
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
            case "Queen Bed" -> "Length: 4ft, Width: 5.5ft";
            case "Dresser" -> "Height: 3ft, Width: 1.5ft";
            case "Nightstand" -> "Height: 2ft, Width: 1.5ft";
            case "Chair" -> "Height: 1.5ft, Width: 1.5ft";
            case "Shelf" -> "Height: , Width: ";
            case "Desk" -> "Height: , Width: ";

            default -> "";
        };
    }
    private Image rotateImageClockwise(Image image, double angle) {

        int width = image.getWidth(null);
        int height = image.getHeight(null);

        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));

        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Move origin to center of new image
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);

        // Rotate the image around its center
        AffineTransform transform = AffineTransform.getRotateInstance(angle, (double) width / 2, (double) height / 2);
        g2d.transform(transform);

        // Draw the original image onto the rotated image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }
    private void drawRoom(Graphics graphics) {
        int panelWidth = roomPanel.getWidth();
        int panelHeight = roomPanel.getHeight();

        graphics.clearRect(0, 0, panelWidth, panelHeight);

        int roomWidthPixels = (int) (panelWidth * roomWidth / 24);
        int roomLengthPixels = (int) (panelHeight * roomLength / 24);

        int startX = (panelWidth - roomWidthPixels) / 2;
        int startY = (panelHeight - roomLengthPixels) / 2;

        graphics.setColor(Color.BLACK);
        graphics.drawRect(startX, startY, roomWidthPixels, roomLengthPixels);
    }

    private void enterNewRoomSize() {
        System.out.println("Entering new room size...");
        dispose();
        BedroomDesignerCLI cli = new BedroomDesignerCLI();
        cli.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BedroomDesignerGUI(0, 0));
    }
}
