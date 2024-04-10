package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    private final double roomLength;
    private final double roomWidth;
    private final Map<String, ImageIcon> furnitureImages;
    private final DragAndDropHandler dragAndDropHandler;

    public BedroomDesignerGUI(double roomLength, double roomWidth) {
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;

        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        initComponents();
        furnitureImages = loadFurnitureImages();
        dragAndDropHandler = new DragAndDropHandler(roomPanel); // Initialize the DragAndDropHandler

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

            // Add the furniture label to the room panel
            roomPanel.add(furnitureLabel);
            roomPanel.revalidate();
            roomPanel.repaint();
        } else {
            System.out.println("Furniture image not found for: " + furnitureName);
        }
    }
    private void rotateIconClockwise() {
        Component[] components = roomPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                Icon icon = ((JLabel) component).getIcon();
                if (icon != null && icon instanceof ImageIcon) {
                    ImageIcon imageIcon = (ImageIcon) icon;
                    Image image = imageIcon.getImage();
                    Image rotatedImage = rotateImageClockwise(image);
                    ((ImageIcon) icon).setImage(rotatedImage);
                    ((JLabel) component).setIcon(icon);
                }
            }
        }
    }

    private Image rotateImageClockwise(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bi = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.rotate(Math.PI / 2, height / 2, height / 2);
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
