package edu.bsu.cs;

import edu.bsu.cs.GUI.FurnitureComboBox;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureComboBoxTest {

    @Test
    public void testFurnitureComboBoxNumberOfItems() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        // Verify that the model contains the correct furniture options
        assertEquals(7, model.getSize());
    }
    @Test
    public void testFurnitureComboBoxSelectFurniture() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Select Furniture", model.getElementAt(0));
    }
    @Test
    public void testFurnitureComboBoxQueenBed() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Queen Bed", model.getElementAt(1));
    }
    @Test
    public void testFurnitureComboBoxDresser() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Dresser", model.getElementAt(2));
    }
    @Test
    public void testFurnitureComboBoxNightstand() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Nightstand", model.getElementAt(3));
    }
    @Test
    public void testFurnitureComboBoxChair() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Chair", model.getElementAt(4));
    }
    @Test
    public void testFurnitureComboBoxDesk() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Desk", model.getElementAt(5));
    }
    @Test
    public void testFurnitureComboBoxShelf() {
        // Create an instance of FurnitureComboBox
        FurnitureComboBox comboBox = new FurnitureComboBox();

        // Get the model of the combo box
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        assertEquals("Shelf", model.getElementAt(6));
    }
}
