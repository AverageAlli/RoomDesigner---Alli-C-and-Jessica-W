package edu.bsu.cs;

public class Error {
    public static Error showError(String message) {
        System.err.println("Error: " + message);
        return null;
    }
}