package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomMakerTest {
    @Test
    public void testAddFurniture() {
        RoomMaker room = new RoomMaker(10.5, 8.2);
        Furniture bed = new Furniture("Bed", 6, 4);

        // Add furniture to the room
        room.addFurniture(bed);

        // Check if the furniture list contains the added furniture
        List<Furniture> furnitureList = room.getFurnitureList();
        assertTrue(furnitureList.contains(bed));
    }

    @Test
    public void testToString() {
        RoomMaker room = new RoomMaker(10.5, 8.2);
        Furniture bed = new Furniture("Bed", 6, 4);
        Furniture table = new Furniture("Table", 4, 3);

        // Add furniture to the room
        room.addFurniture(bed);
        room.addFurniture(table);

        // Verify the toString() method
        String expectedToString = "Room Dimensions: 10x8\n" +
                "Furniture:\n" +
                "- Bed\n" +
                "- Table\n";
        assertEquals(expectedToString, room.toString());
    }
}

