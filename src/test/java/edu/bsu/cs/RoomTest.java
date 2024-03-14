package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {
    @Test
    public void testValidRoomMakerToString(){
        //Arrange
        int length = 5;
        int width = 5;
        RoomMaker roomMaker = new RoomMaker(length, width);
        String expected = "Room: Length=5, Width=5";

        // Act
        String actual = roomMaker.toString();

        // Assert
        assertEquals(expected, actual);
    }
    }

