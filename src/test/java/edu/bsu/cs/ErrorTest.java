package edu.bsu.cs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ErrorTest extends Application {

    public static void main(String[] args) {
        // Launch a simple JavaFX application to initialize the toolkit
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // This method is called when the JavaFX application is launched
        // You can leave this method empty
    }

    @Override
    public void stop() {
        // Stop the JavaFX application when the test is finished
        System.exit(0);
    }

    public void testError() {
        // Test showing an error message
        showError("This is an error message.");

        // Test showing another error message
        showError("Another error occurred.");
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}