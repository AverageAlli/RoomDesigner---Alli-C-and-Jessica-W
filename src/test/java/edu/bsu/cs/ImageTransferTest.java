
package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ImageTransferTest {
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
        public void testGetTransferData() throws UnsupportedFlavorException, IOException {
        Image image = new ImageIcon("src/main/ObjectImages/Bed.png").getImage();
        ImageTransfer imageTransfer = new ImageTransfer(image);

        // Verify that getTransferData returns the expected Image
        assertEquals(image, imageTransfer.getTransferData(DataFlavor.imageFlavor));
        }
        }
