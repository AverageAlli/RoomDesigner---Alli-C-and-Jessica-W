package edu.bsu.cs;

import org.junit.jupiter.api.Test;

public class FurnitureTest {
    @Test
    public void testMakeFurniture(){
        Furniture dresser = new Furniture("Dresser",4,1.5);
        Furniture nightstand = new Furniture("Nightstand",2,1);

        System.out.println("Name of dresser: " + dresser.getName());
        System.out.println("Width of dresser: " + dresser.getWidth());
        System.out.println("Length of dresser: " + dresser.getLength());

        nightstand.setName("End Table");
        nightstand.setWidth(3);
        nightstand.setLength(4);

        System.out.println("Name of nightstand: " + nightstand.getName());
        System.out.println("Width of nightstand: " + nightstand.getWidth());
        System.out.println("Length of nightstand: " + nightstand.getLength());
    }



}
