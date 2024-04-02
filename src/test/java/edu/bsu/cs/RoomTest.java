package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {
    private RoomMaker roomMaker;
    private Furniture chair;
    private Furniture table;

//    @BeforeEach
//    public void setUp() {
//        roomMaker = new RoomMaker(10.5, 8.3);
//        chair = new Furniture("Chair");
//        table = new Furniture("Table");
//    }

    @Test
    public void testAddFurniture() {
        roomMaker.addFurniture(chair);
        roomMaker.addFurniture(table);
        List<Furniture> expectedFurnitureList = new ArrayList<>();
        expectedFurnitureList.add(chair);
        expectedFurnitureList.add(table);
        assertEquals(expectedFurnitureList, roomMaker.getFurnitureList());
    }

    @Test
    public void testToString() {
        roomMaker.addFurniture(chair);
        roomMaker.addFurniture(table);
        String expectedString = "Room Dimensions: 10x8\n" +
                "Furniture:\n" +
                "- Chair\n" +
                "- Table\n";
        assertEquals(expectedString, roomMaker.toString());
    }

    @Test
    public void testGetLength() {
        assertEquals(10, roomMaker.getLength());
    }

    @Test
    public void testGetWidth() {
        assertEquals(8, roomMaker.getWidth());
    }

    @Test
    public void testSetLength() {
        roomMaker.setLength(12);
        assertEquals(12, roomMaker.getLength());
    }

    @Test
    public void testSetWidth() {
        roomMaker.setWidth(9);
        assertEquals(9, roomMaker.getWidth());
    }

    @Test
    public void testSetFurnitureList() {
        List<Furniture> newFurnitureList = new ArrayList<>();
        newFurnitureList.add(chair);
        roomMaker.setFurnitureList(newFurnitureList);
        assertEquals(newFurnitureList, roomMaker.getFurnitureList());
    }

    }

