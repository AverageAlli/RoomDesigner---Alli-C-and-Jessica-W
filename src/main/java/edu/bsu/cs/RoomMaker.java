package edu.bsu.cs;

import java.util.Scanner;

public class RoomMaker {
    private int length;
    private int width;

    public RoomMaker(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        return "Square: Length=" + length + ", Width=" + width;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Length of room:");
        int length = scanner.nextInt();
        System.out.println("Enter Width of room:");
        int width = scanner.nextInt();
        RoomMaker roomMaker = new RoomMaker(length, width);
        System.out.println(roomMaker);

    }
}
