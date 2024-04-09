package edu.bsu.cs;

import java.util.Scanner;

public class BedroomDesignerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bedroom Designer by Alli and Jess!");

        while (true) {
            System.out.println("Enter the length of the room (in feet) or type 'e' to exit the program.");
            String lengthInput = scanner.nextLine();

            if (lengthInput.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.println("Enter the width of the room (in feet) or type 'e' to exit the program.");
            String widthInput = scanner.nextLine();

            if (widthInput.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                break;
            }
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
/*package edu.bsu.cs;

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
}*/
