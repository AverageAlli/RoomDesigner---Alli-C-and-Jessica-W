package edu.bsu.cs;

import edu.bsu.cs.Model.ImageTransfer;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ImageTransferTest {
    private BufferedImage toBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    @Test
    public void testTransferDataFlavors() {
        Image image = new ImageIcon("src/main/ObjectImages/Bed.png").getImage();
        ImageTransfer imageTransfer = new ImageTransfer(image);
        DataFlavor[] flavors = imageTransfer.getTransferDataFlavors();

        // Verify that the only supported flavor is DataFlavor.imageFlavor
        assertEquals(1, flavors.length);
        assertTrue(flavors[0].equals(DataFlavor.imageFlavor));
    }

        @Test
        public void testIsDataFlavorSupported() {
        Image image = new ImageIcon("src/main/ObjectImages/Bed.png").getImage();
        ImageTransfer imageTransfer = new ImageTransfer(image);

        // Verify that DataFlavor.imageFlavor is supported
        assertTrue(imageTransfer.isDataFlavorSupported(DataFlavor.imageFlavor));
        }
    @Test
    public void testTransferDataWidth() throws UnsupportedFlavorException, IOException {
        // Load the image from file
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/ObjectImages/Bed.png");

        // Create an instance of the ImageTransfer class
        ImageTransfer imageTransfer = new ImageTransfer(image);

        // Get the transfer data
        Object transferData = imageTransfer.getTransferData(DataFlavor.imageFlavor);

        // Convert the transfer data to BufferedImage
        BufferedImage transferredBufferedImage = toBufferedImage((Image) transferData);

        // Load the original image separately
        Image originalImage = Toolkit.getDefaultToolkit().getImage("src/main/ObjectImages/Bed.png");

        // Convert the original image to BufferedImage
        BufferedImage originalBufferedImage = toBufferedImage(originalImage);

        // Compare dimensions
        assertEquals(originalBufferedImage.getWidth(), transferredBufferedImage.getWidth());
    }
    @Test
    public void testGetTransferDataImageHeight() throws UnsupportedFlavorException, IOException {
        // Load the image from file
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/ObjectImages/Bed.png");

        // Create an instance of the ImageTransfer class
        ImageTransfer imageTransfer = new ImageTransfer(image);

        // Get the transfer data
        Object transferData = imageTransfer.getTransferData(DataFlavor.imageFlavor);

        // Convert the original image to BufferedImage
        BufferedImage originalBufferedImage = toBufferedImage(image);

        // Convert the transfer data to BufferedImage
        BufferedImage transferredBufferedImage = toBufferedImage((Image) transferData);

        // Compare dimensions
        assertEquals(originalBufferedImage.getHeight(), transferredBufferedImage.getHeight());
    }

    @Test
    public void testGetTransferDataPixelColor() throws UnsupportedFlavorException, IOException {
        // Load the image from file
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/ObjectImages/Bed.png");

        // Create an instance of the ImageTransfer class
        ImageTransfer imageTransfer = new ImageTransfer(image);

        // Get the transfer data
        Object transferData = imageTransfer.getTransferData(DataFlavor.imageFlavor);

        // Convert the original image to BufferedImage
        BufferedImage originalBufferedImage = toBufferedImage(image);

        // Convert the transfer data to BufferedImage
        BufferedImage transferredBufferedImage = toBufferedImage((Image) transferData);

        // Compare pixel colors
        for (int x = 0; x < originalBufferedImage.getWidth(); x++) {
            for (int y = 0; y < originalBufferedImage.getHeight(); y++) {
                assertEquals(originalBufferedImage.getRGB(x, y), transferredBufferedImage.getRGB(x, y));
            }
        }
    }
}
