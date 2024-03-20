package edu.bsu.cs;

import javax.swing.*;
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
}