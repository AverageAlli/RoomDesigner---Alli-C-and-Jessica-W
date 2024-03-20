package edu.bsu.cs;

import javax.swing.*;
import java.awt.*;

public class BedroomDesignerGUI extends JFrame {

    private JPanel roomPanel;
    int roomSize;

    public BedroomDesignerGUI(int roomSize) {
        setTitle("Room Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        initComponents();

        setVisible(true);
    }
    private void initComponents() {
        roomPanel = new JPanel();
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

    private void drawRoom(Graphics graphics) {
        int width = 500; // Width of the roomPanel
        int height = 500; // Height of the roomPanel

        // Clear the panel before drawing
        graphics.clearRect(0, 0, roomPanel.getWidth(), roomPanel.getHeight());

        // Draw the outline of the room based on the selected room size
        graphics.setColor(Color.BLACK);
        switch (roomSize) {
            case 1: // 9x16
                graphics.drawRect(width / 12, height / 12, width * 4 / 6, height * 4 / 6);
                break;
            case 2: // 10x8
                graphics.drawRect(width / 8, height / 8, width / 1, height / 1);
                break;
            case 3: // 10x12
                graphics.drawRect(width / 8, height / 6, width / 1, height * 4 / 3);
                break;
            case 4: // 10x14
                graphics.drawRect(width / 8, height / 6, width / 1, height * 10 / 6);
                break;
            case 5: // 12x8
                graphics.drawRect(width / 12, height / 4, width * 4 / 6, height / 1);
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