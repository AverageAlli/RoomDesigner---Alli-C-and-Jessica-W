package edu.bsu.cs;
import edu.bsu.cs.View.BedroomDesignerGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class DisplayFurnitureImageTest {

    @Test
    void testDisplayFurnitureImage_ValidName() {
        double roomLength = 10;
        double roomWidth = 10;
        BedroomDesignerGUI gui = new BedroomDesignerGUI(roomLength, roomWidth);
        gui.initComponents();

        String validFurnitureName = "Queen Bed";
        gui.displayFurnitureImage(validFurnitureName);

        boolean foundFurniture = false;
        for (Component component : gui.getRoomPanel().getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null && label.getToolTipText().equals("Dimensions: Length: 4ft, Width: 5.5ft")) {
                    foundFurniture = true;
                    break;
                }
            }
        }
        assertTrue(foundFurniture, "Furniture image not found in room panel for valid furniture name.");
    }

    @Test
    void testDisplayFurnitureImage_NullName() {
        double roomLength = 10;
        double roomWidth = 10;
        BedroomDesignerGUI gui = new BedroomDesignerGUI(roomLength, roomWidth);
        gui.initComponents();

        String nullFurnitureName = null;
        gui.displayFurnitureImage(nullFurnitureName);

        assertEquals(0, gui.getRoomPanel().getComponentCount(), "No furniture image should be added for null furniture name.");
    }

    @Test
    void testDisplayFurnitureImage_InvalidName() {
        double roomLength = 10;
        double roomWidth = 10;
        BedroomDesignerGUI gui = new BedroomDesignerGUI(roomLength, roomWidth);
        gui.initComponents();

        String invalidFurnitureName = "InvalidFurniture";
        gui.displayFurnitureImage(invalidFurnitureName);

        assertEquals(0, gui.getRoomPanel().getComponentCount(), "No furniture image should be added for invalid furniture name.");
    }

    @Test
    void testDisplayFurnitureImage_ImageNotFound() {
        double roomLength = 10;
        double roomWidth = 10;
        BedroomDesignerGUI gui = new BedroomDesignerGUI(roomLength, roomWidth);
        gui.initComponents();

        String nonExistentFurnitureName = "NonExistentFurniture";
        gui.displayFurnitureImage(nonExistentFurnitureName);

        assertEquals(0, gui.getRoomPanel().getComponentCount(), "No furniture image should be added for non-existent furniture name.");
    }
}
