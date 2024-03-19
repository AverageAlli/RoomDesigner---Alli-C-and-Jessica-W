package edu.bsu.cs;

import java.util.Scanner;

public class BedroomDesignerCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter room dimensions
        System.out.println("Welcome to Bedroom Designer!");
        System.out.println("Please enter the dimensions of your room (length and width):");

        double length = 0;
        double width = 0;
        boolean validDimensions = false;

        // Keep prompting until valid dimensions are entered
        while (!validDimensions) {
            System.out.print("Length (in feet): ");
            length = scanner.nextDouble();
            System.out.print("Width (in feet): ");
            width = scanner.nextDouble();
            validDimensions = Validation.isValidRoomDimensions(length, width);
        }

        // Create room with entered dimensions
        RoomMaker room = new RoomMaker(length, width);

        // Add some predefined furniture to the room
        room.addFurniture(new Furniture("Bed", 80.0, 60.0));
        room.addFurniture(new Furniture("Dresser", 50.0, 30.0));
        room.addFurniture(new Furniture("Desk", 40.0, 25.0));

        // Display the room details
        System.out.println("\nYour room:");
        System.out.println(room);

        scanner.close();
    }
}/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        Parent root = loader.load();

        // Set controller
        RoomDesignerController controller = loader.getController();

        // Create scene
        Scene scene = new Scene(root);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room Designer");
        primaryStage.show();

        // Call the startup method after the UI is initialized
        startup(() -> {
            // Your code to run after the UI is initialized
            System.out.println("Application is starting...");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startup(Runnable runnable) {
        // Method to execute the provided Runnable
        runnable.run();
    }
}*/