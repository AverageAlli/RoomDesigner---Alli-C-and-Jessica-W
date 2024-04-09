package edu.bsu.cs;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

public class ImageTransfer implements Transferable {
    private Image image;

    private static final DataFlavor[] flavors = new DataFlavor[]{DataFlavor.imageFlavor};

    public ImageTransfer(Image image) {
        this.image = image;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        if (image instanceof RenderedImage) {
            return image;
        } else {
            // If the image is not a RenderedImage, attempt to convert it
            int width = image.getWidth(null); // Get the width of the image
            int height = image.getHeight(null); // Get the height of the image

            // Check if width and height are valid
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Invalid image dimensions");
            }

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
            return bufferedImage;
        }
    }
    }

