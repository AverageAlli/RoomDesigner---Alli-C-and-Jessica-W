package edu.bsu.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../resources/GUI.fxml"));
        Parent root = loader.load();

        // Set controller
        RoomDesignerController controller = loader.getController();
        // You may need to pass any necessary data to the controller
        // For example:
        // controller.initData();

        // Create scene
        Scene scene = new Scene(root);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room Designer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
