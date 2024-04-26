package edu.bsu.cs.Model;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;


public class ImageTransfer implements Transferable {
    private final Image image;

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
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        if (image instanceof RenderedImage) {
            return image;
        } else {

            int width = image.getWidth(null);
            int height = image.getHeight(null);


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


