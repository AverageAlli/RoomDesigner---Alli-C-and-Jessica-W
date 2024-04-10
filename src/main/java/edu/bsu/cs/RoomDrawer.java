package edu.bsu.cs;

import java.awt.*;

public class RoomDrawer {
    public static void drawRoom(Graphics graphics, int panelWidth, int panelHeight, double length, double width) {
        // Clear the panel before drawing
        graphics.clearRect(0, 0, panelWidth, panelHeight);

        // Draw the outline of the room based on the provided room size (length and width)
        graphics.setColor(Color.BLACK);
        int roomWidth = (int) (panelWidth * width);
        int roomHeight = (int) (panelHeight * length);
        int startX = (panelWidth - roomWidth) / 2;
        int startY = (panelHeight - roomHeight) / 2;
        graphics.drawRect(startX, startY, roomWidth, roomHeight);
    }
}
