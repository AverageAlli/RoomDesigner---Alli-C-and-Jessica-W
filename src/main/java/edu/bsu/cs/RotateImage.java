package edu.bsu.cs;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class RotateImage {
    public static Image rotateImageClockwise(Image image, double angle) {

        int width = image.getWidth(null);
        int height = image.getHeight(null);

        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));

        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Move origin to center of new image
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);

        // Rotate the image around its center
        AffineTransform transform = AffineTransform.getRotateInstance(angle, (double) width / 2, (double) height / 2);
        g2d.transform(transform);

        // Draw the original image onto the rotated image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }
}
