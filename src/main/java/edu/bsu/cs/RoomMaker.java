package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class RoomMaker {
    private int length;
    private int width;
    private List<Furniture> furnitureList;

    public RoomMaker(double length, double width) {
        this.length = (int) length;
        this.width = (int) width;
        this.furnitureList = new ArrayList<>();
    }

    public void addFurniture(Furniture furniture) {
        furnitureList.add(furniture);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room Dimensions: ").append(length).append("x").append(width).append("\n");
        sb.append("Furniture:\n");
        for (Furniture furniture : furnitureList) {
            sb.append("- ").append(furniture.getName()).append("\n");
        }
        return sb.toString();
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }
    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }
}