package edu.bsu.cs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RoomDesignerController {
    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private ComboBox<String> furnitureComboBox;

    private RoomMaker roomMaker;

    public void initialize() {
        furnitureComboBox.getItems().addAll("TwinBed", "KingBed", "QueenBed", "Desk", "LongDresser", "TallDresser", "NightStand");
    }

    @FXML
    private void generateRoom() {
        try {
            double length = Double.parseDouble(lengthTextField.getText());
            double width = Double.parseDouble(widthTextField.getText());

            if (!Validation.isValidRoomDimensions(length, width)) {
                return;
            }

            // Assuming you have a method to create room based on dimensions and selected furniture
            roomMaker = new RoomMaker(length, width);
            String selectedFurniture = furnitureComboBox.getValue();
            Furniture furniture = new Furniture(selectedFurniture, 0, 0); // You may adjust the width and length
            roomMaker.addFurniture(furniture); // Assuming you have a method to add furniture to the room
            // Call method to visualize the room
        } catch (NumberFormatException e) {
            Error.showError("Invalid input. Please enter valid numbers for length and width.");
        }
    }
}