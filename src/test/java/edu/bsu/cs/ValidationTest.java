package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {
    @Test
    public void testValidRoomDimensions() {
        // Test with valid room dimensions
        assertTrue(Validation.isValidRoomDimensions(10.5, 8.2));
    }
    @Test
    public void testInvalidRoomDimensions_NegativeLength() {
        // Test with negative length
        assertFalse(Validation.isValidRoomDimensions(-10.5, 8.2));
    }
    @Test
    public void testInvalidRoomDimensions_NegativeWidth() {
        // Test with negative width
        assertFalse(Validation.isValidRoomDimensions(10.5, -8.2));
    }
    @Test
    public void testInvalidRoomDimensions_ZeroLength() {
        // Test with zero length
        assertFalse(Validation.isValidRoomDimensions(0, 8.2));
    }
    @Test
    public void testInvalidRoomDimensions_ZeroWidth() {
        // Test with zero width
        assertFalse(Validation.isValidRoomDimensions(10.5, 0));
    }
}