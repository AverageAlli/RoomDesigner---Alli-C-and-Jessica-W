package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.ImageIcon;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureImageLoaderTest {

    private FurnitureImageLoader imageLoader;

    @BeforeEach
    public void setUp() {
        imageLoader = new FurnitureImageLoader();
    }

    @Test
    public void testLoadFurnitureImages() {
        assertNotNull(imageLoader);

        // Check individual furniture images
        assertNotNull(imageLoader.getFurnitureImage("Bed"));
        assertNotNull(imageLoader.getFurnitureImage("Dresser"));
        assertNotNull(imageLoader.getFurnitureImage("Nightstand"));
        assertNotNull(imageLoader.getFurnitureImage("Chair"));
    }
}