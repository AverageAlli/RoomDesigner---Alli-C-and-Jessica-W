package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BedroomDesignerGUI extends JFrame {

    private static final String[] FURNITURE_NAMES = {"Bed", "Nightstand", "Dresser", "Chair"};
    private static final String[] FURNITURE_IMAGE_NAMES = {"bed.png", "nightstand.png", "dresser.png", "chair.png"};
    private static final String ROOM_LAYOUTS_DIRECTORY = "/Users/allicarr/IdeaProjects/FinalProject-JessicaAlli/src/main/roomLayouts/";

    private JPanel roomPanel;
    private JComboBox<String> furnitureComboBox;

    public BedroomDesignerGUI(int roomSize) {
        setTitle("Bedroom Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new BorderLayout());

        // Load background image based on room size
        String roomImagePath = ROOM_LAYOUTS_DIRECTORY + "room" + roomSize + ".png";
        ImageIcon backgroundImageIcon = new ImageIcon(roomImagePath);
        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        roomPanel = new JPanel(new BorderLayout());
        roomPanel.add(backgroundLabel);

        // Add furniture combo box
        furnitureComboBox = new JComboBox<>(FURNITURE_NAMES);
        furnitureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFurniture = (String) furnitureComboBox.getSelectedItem();
                String furnitureImageName = getFurnitureImageName(selectedFurniture);
                String furnitureImagePath = ROOM_LAYOUTS_DIRECTORY + furnitureImageName;
                ImageIcon furnitureImageIcon = new ImageIcon(furnitureImagePath);
                JLabel furnitureLabel = new JLabel(furnitureImageIcon);
                furnitureLabel.setSize(furnitureImageIcon.getIconWidth(), furnitureImageIcon.getIconHeight());
                furnitureLabel.setLocation(0, 0);
                roomPanel.add(furnitureLabel);
                roomPanel.revalidate();
                roomPanel.repaint();
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(furnitureComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(roomPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private String getFurnitureImageName(String furnitureName) {
        for (int i = 0; i < FURNITURE_NAMES.length; i++) {
            if (FURNITURE_NAMES[i].equals(furnitureName)) {
                return FURNITURE_IMAGE_NAMES[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BedroomDesignerGUI(1); // Default room size
            }
        });
    }
}
/*import javax.swing.*;
import java.awt.*;


public class BedroomDesignerGUI extends JFrame {

    private RoomMaker room;
    private JPanel roomPanel;

    public BedroomDesignerGUI(double length, double width) {
        setTitle("Bedroom Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        room = new RoomMaker(length, width);

        roomPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int side = Math.min(getWidth(), getHeight()) - 20;
                int x = (getWidth() - side) / 2;
                int y = (getHeight() - side) / 2;
                g.setColor(Color.GRAY);
                g.fillRect(x, y, side, side);

                for (Furniture furniture : room.getFurnitureList()) {
                    if (furniture.getName().equals("Bed")) {
                        g.setColor(Color.BLUE);
                    } else if (furniture.getName().equals("Dresser")) {
                        g.setColor(Color.RED);
                    } else if (furniture.getName().equals("Nightstand")) {
                        g.setColor(Color.GREEN);
                    } else if (furniture.getName().equals("Chair")) {
                        g.setColor(Color.YELLOW);
                    }

                }
            }
        };

        add(roomPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        double length, width;
        if (args.length >= 2) {
            try {
                length = Double.parseDouble(args[0]);
                width = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid dimensions. Please enter valid numbers.");
                return;
            }

            if (!Validation.isValidRoomDimensions(length, width)) {
                System.out.println("Invalid room dimensions. Please enter valid dimensions.");
                return;
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new BedroomDesignerGUI(length, width).setVisible(true);
                }
            });
        } else {
            System.out.println("Please provide the room dimensions (length and width) as command line arguments.");
        }
    }
}*/