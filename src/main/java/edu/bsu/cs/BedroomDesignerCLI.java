package edu.bsu.cs;

import java.util.Scanner;

public class BedroomDesignerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bedroom Designer by Alli and Jess!");
        System.out.println("Please select one of the 6 pre-selected floor plans below or type 'e' to exit:");
        System.out.println("1. 9x16");
        System.out.println("2. 10x8");
        System.out.println("3. 10x12");
        System.out.println("4. 10x14");
        System.out.println("5. 12x8");
        System.out.println("6. 12x12");

        while (true) {
            System.out.print("Enter your choice (1-6) or 'e' to exit: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                break;
            }

            int roomSize;
            try {
                roomSize = Integer.parseInt(choice);
                if (roomSize < 1 || roomSize > 6) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number between 1 and 6 or 'e' to exit.");
                continue;
            }

            openBedroomDesignerGUI(roomSize);
        }
    }

    private static void openBedroomDesignerGUI(int roomSize) {
        System.out.println("You chose layout option " + roomSize + ". Generating GUI...");
        new BedroomDesignerGUI(roomSize);
    }
}

/*import javax.swing.*;
import java.util.Scanner;

public class BedroomDesignerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Bedroom Designer!");
            System.out.println("Please enter the dimensions of your room (length and width):");

            System.out.print("Length (in feet): ");
            double length = scanner.nextDouble();

            System.out.print("Width (in feet): ");
            double width = scanner.nextDouble();

            // Validate room dimensions
            if (!Validation.isValidRoomDimensions(length, width)) {
                Error.showError("Invalid room dimensions. Length and width must be positive.");
                continue; // Go back to the beginning of the loop to ask for dimensions again
            }

            RoomMaker room = new RoomMaker(length, width);

            System.out.println("Select furniture to add to the room:");
            System.out.println("1. Bed");
            System.out.println("2. Dresser");
            System.out.println("3. Nightstand");
            System.out.println("4. Chair");
            System.out.println("5. Done");

            int choice;
            do {
                System.out.print("Enter your choice (1-5): ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        room.addFurniture(new Furniture("Bed", 6, 6)); // Example dimensions, you can change them
                        break;
                    case 2:
                        room.addFurniture(new Furniture("Dresser", 4, 2));
                        break;
                    case 3:
                        room.addFurniture(new Furniture("Nightstand", 2, 2));
                        break;
                    case 4:
                        room.addFurniture(new Furniture("Chair", 3, 3));
                        break;
                    case 5:
                        break;
                    default:
                        Error.showError("Invalid choice. Please enter a number between 1 and 5.");
                        break;
                }
            } while (choice != 5);

            System.out.println("\nYour room details:");
            System.out.println(room);

            // Ask if the user wants to design another room
            System.out.print("Do you want to design another room? (yes/no): ");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("yes")) {
                break; // Exit the loop if the user doesn't want to design another room
            }
        }

        // Open GUI after CLI finishes
        SwingUtilities.invokeLater(() -> {
            JFrame guiFrame = new BedroomDesignerGUI(10, 10); // Example dimensions, you can change them
            guiFrame.setVisible(true);
        });

        System.out.println("Thank you for using Bedroom Designer. Goodbye!");
    }}*/