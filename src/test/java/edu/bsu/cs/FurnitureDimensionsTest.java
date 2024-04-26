package edu.bsu.cs;

import edu.bsu.cs.Model.FurnitureDimensions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FurnitureDimensionsTest {

    @Test
    public void testDimensionsQueenBed() {
        // Test for Queen Bed
        assertEquals("Length: 4ft, Width: 5.5ft", FurnitureDimensions.getFurnitureDimensions("Queen Bed"));
    }
    @Test
    public void testDimensionsDresser() {
        assertEquals("Length: 3ft, Width: 1.5ft", FurnitureDimensions.getFurnitureDimensions("Dresser"));
    }
    @Test
    public void testDimensionsNightstand() {
        assertEquals("Length: 2ft, Width: 1.5ft", FurnitureDimensions.getFurnitureDimensions("Nightstand"));
    }
    @Test
    public void testDimensionsChair() {
        assertEquals("Length: 1.5ft, Width: 1.5ft", FurnitureDimensions.getFurnitureDimensions("Chair"));
    }
    @Test
    public void testDimensionsShelf() {
        assertEquals("Length: 4ft, Width: 1.5ft", FurnitureDimensions.getFurnitureDimensions("Shelf"));
    }
    @Test
    public void testDimensionsDesk() {
        assertEquals("Length: 3ft, Width: 2ft", FurnitureDimensions.getFurnitureDimensions("Desk"));
    }
    @Test
    public void testDimensionsUnknown() {
        assertEquals("", FurnitureDimensions.getFurnitureDimensions("Unknown"));
    }
}
