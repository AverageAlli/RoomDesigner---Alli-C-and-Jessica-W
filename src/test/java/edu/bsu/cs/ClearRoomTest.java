package edu.bsu.cs;

import edu.bsu.cs.GUI.ClearRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
public class ClearRoomTest {

    @Test
    public void testClearRoom() {
        // Create a room panel with some components
        JPanel roomPanel = new JPanel();
        roomPanel.add(new JLabel("Component 1"));
        roomPanel.add(new JLabel("Component 2"));

        // Set initial last furniture position
        Point lastFurniturePosition = new Point(10, 10);
        ClearRoom.clearRoom(roomPanel);

        // Assert that the room panel is empty
        Assertions.assertEquals(0, roomPanel.getComponentCount());

    }
}
