package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoomDesignerController extends Application {
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
            Furniture furniture = new Furniture(selectedFurniture, 0, 0); //Adjust width and length
            roomMaker.addFurniture(furniture);
        } catch (NumberFormatException e) {
            Error.showError("Invalid input. Please enter valid numbers for length and width.");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}