package edu.bsu.cs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ErrorTest extends Application {

    public static void main(String[] args) {
        // Launch a simple JavaFX application to initialize the toolkit
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        // This method is called when the JavaFX application is launched
        // You can leave this method empty
    }

    @Override
    public void stop() {
        // Stop the JavaFX application when the test is finished
        Platform.exit();
    }
    @Override
    public void init() throws Exception {
        super.init();
        // Add any cleanup code here
    }
    public void testError() {
        // Test showing an error message
        Error.showError("This is an error message.");

        // Test showing another error message
        Error.showError("Another error occurred.");
    }
}