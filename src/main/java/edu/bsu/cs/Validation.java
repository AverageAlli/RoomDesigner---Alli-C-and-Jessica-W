package edu.bsu.cs;

public class Validation {
   public static boolean isValidRoomDimensions(double length, double width) {
        if (length <= 0 || width <= 0) {
            Error.showError("Invalid room dimensions. Length and width must be positive.");
            return false;
        }
        return true;
    }
}
//Still need to add a maximum for room size, need to do testing on personal computers to determine what that is