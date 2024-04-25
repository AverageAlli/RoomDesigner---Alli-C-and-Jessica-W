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
    }
    @Test
    public void testLoadBedImage(){
        assertNotNull(imageLoader.getFurnitureImage("Bed"));
    }
    @Test
    public void testLoadDresserImage(){
        assertNotNull(imageLoader.getFurnitureImage("Dresser"));
    }
    @Test
    public void testLoadNightstandImage(){
        assertNotNull(imageLoader.getFurnitureImage("Nightstand"));
    }
    @Test
    public void testLoadChairImage(){
        assertNotNull(imageLoader.getFurnitureImage("Chair"));
    }
}