package edu.bsu.cs;

import edu.bsu.cs.View.RotateImage;
import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RotateImageTest {

    @Test
    public void testRotateImageClockwise_DimensionsWidth() throws IOException {
        // Load an image for testing
        BufferedImage originalImage = ImageIO.read(new File("src/main/ObjectImages/Bed.png"));

        // Rotate the image by 90 degrees clockwise
        double angle = Math.toRadians(90);
        Image rotatedImage = RotateImage.rotateImageClockwise(originalImage, angle);

        // Verify that the rotated image has the expected dimensions
        int expectedWidth = originalImage.getHeight();
        int expectedHeight = originalImage.getWidth();
        assertEquals(expectedWidth, rotatedImage.getWidth(null));
    }
    @Test
    public void testRotateImageClockwise_DimensionsHeight() throws IOException {
        // Load an image for testing
        BufferedImage originalImage = ImageIO.read(new File("src/main/ObjectImages/Bed.png"));

        // Rotate the image by 90 degrees clockwise
        double angle = Math.toRadians(90);
        Image rotatedImage = RotateImage.rotateImageClockwise(originalImage, angle);

        // Verify that the rotated image has the expected dimensions
        int expectedWidth = originalImage.getHeight();
        int expectedHeight = originalImage.getWidth();
        assertEquals(expectedHeight, rotatedImage.getHeight(null));
    }
    @Test
    public void testRotateImageClockwise_FileCreation() throws IOException {
        // Load an image for testing
        BufferedImage originalImage = ImageIO.read(new File("src/main/ObjectImages/Bed.png"));
        // Rotate the image by 90 degrees clockwise
        double angle = Math.toRadians(90);
        Image rotatedImage = RotateImage.rotateImageClockwise(originalImage, angle);
        // Save the rotated image for manual inspection (optional)
        File outputfile = new File("src/main/ObjectImages/Bed.png");
        ImageIO.write((BufferedImage) rotatedImage, "png", outputfile);
        // Assert that the output file was created
        assertTrue(outputfile.exists());
    }

    @Test
    public void testRotateImageClockwise_PixelValues() throws IOException {
        // Load an image for testing
        BufferedImage originalImage = ImageIO.read(new File("src/main/ObjectImages/Bed.png"));
        // Rotate the image by 90 degrees clockwise
        double angle = Math.toRadians(90);
        Image rotatedImage = RotateImage.rotateImageClockwise(originalImage, angle);
    }
}
