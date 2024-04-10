package edu.bsu.cs;

import java.util.Scanner;

public class BedroomDesignerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Welcome message
        System.out.println("Welcome to the Bedroom Designer by Alli and Jess!");

        while (true) {
            //Prompt user to enter dimensions of room in feet, starting with length first
            System.out.println("Enter the length of the room (in feet) or type 'e' to exit the program.");
            String lengthInput = scanner.nextLine();
            //If user enters character "e", exit program
            if (lengthInput.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                break;
            }
            //Prompt user to enter width of room in feet
            System.out.println("Enter the width of the room (in feet) or type 'e' to exit the program.");
            String widthInput = scanner.nextLine();
            //If user enters character "e", exit program
            if (widthInput.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                break;
            }
            //Error handling for when user inputs 0 or a negative value
            try {
                double length = Double.parseDouble(lengthInput);
                double width = Double.parseDouble(widthInput);
                if (length <= 0 || width <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Error.showError("Invalid entry for room dimensions. Values must be greater than 0.");
                continue;
            }

            openBedroomDesignerGUI(Double.parseDouble(lengthInput), Double.parseDouble(widthInput));
        }
    }

    private static void openBedroomDesignerGUI(double roomLength, double roomWidth) {
        System.out.println("You chose a room with a size of " + roomLength + "x" + roomWidth + ". Generating GUI...");
        new BedroomDesignerGUI(roomLength, roomWidth);
    }
}
