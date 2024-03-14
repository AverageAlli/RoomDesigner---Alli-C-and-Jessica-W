package edu.bsu.cs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Error {
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//use by calling Error.showError("MESSAGE")

}
