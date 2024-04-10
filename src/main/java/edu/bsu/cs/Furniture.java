package edu.bsu.cs;



public class Furniture {
    private String name;
    private double width;
    private double length;

    public Furniture(String name, double width, double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }
    //Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
}