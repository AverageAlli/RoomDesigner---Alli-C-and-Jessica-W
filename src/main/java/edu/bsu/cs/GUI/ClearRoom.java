package edu.bsu.cs.GUI;

import javax.swing.*;
import java.awt.*;

public class ClearRoom {
    public static void clearRoom(JPanel roomPanel) {
        roomPanel.removeAll();
        roomPanel.revalidate();
        roomPanel.repaint();
    }
}
