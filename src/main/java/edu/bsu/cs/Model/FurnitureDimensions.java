package edu.bsu.cs.Model;

public class FurnitureDimensions {
    public static String getFurnitureDimensions(String furnitureName) {
        return switch (furnitureName) {
            case "Queen Bed" -> "Length: 4ft, Width: 5.5ft";
            case "Dresser" -> "Length: 3ft, Width: 1.5ft";
            case "Nightstand" -> "Length: 2ft, Width: 1.5ft";
            case "Chair" -> "Length: 1.5ft, Width: 1.5ft";
            case "Shelf" -> "Length: 4ft, Width: 1.5ft";
            case "Desk" -> "Length: 3ft, Width: 2ft";

            default -> "";
        };
    }
}
