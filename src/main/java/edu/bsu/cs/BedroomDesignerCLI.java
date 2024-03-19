package edu.bsu.cs;

import java.util.Scanner;

public class BedroomDesignerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Bedroom Designer!");
        System.out.println("Please enter the dimensions of your room (length and width):");

        System.out.print("Length (in feet): ");
        double length = scanner.nextDouble();

        System.out.print("Width (in feet): ");
        double width = scanner.nextDouble();

        // Validate room dimensions
        if (!Validation.isValidRoomDimensions(length, width)) {
            return; // Exit program if room dimensions are invalid
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
                    Error.showError("Invalid choice. Please enter a number between 1 and 5."); //error if invalid choice is made
                    break;
            }
        } while (choice != 5);

        System.out.println("\nYour room details:");
        System.out.println(room);
    }
}