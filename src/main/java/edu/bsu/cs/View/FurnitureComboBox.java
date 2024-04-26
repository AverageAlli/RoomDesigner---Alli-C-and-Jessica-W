package edu.bsu.cs.View;

import javax.swing.*;

public class FurnitureComboBox extends JComboBox<String> {
    public FurnitureComboBox() {
        String[] furnitureOptions = {"Select Furniture", "Queen Bed", "Dresser", "Nightstand", "Chair", "Desk", "Shelf"};
        setModel(new DefaultComboBoxModel<>(furnitureOptions));
    }
}