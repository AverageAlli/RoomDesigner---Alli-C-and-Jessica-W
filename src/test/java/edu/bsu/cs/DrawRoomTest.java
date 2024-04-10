/*package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrawRoomTest {
    @Test
    public void testDrawRoom() {
        // Create a mock Graphics object for testing purposes
        Graphics graphics = new MockGraphics();

        // Create a DrawRoom instance with roomSize 1
        DrawRoom drawRoom = new DrawRoom(graphics, 1);

        // Call the drawRoom method
        drawRoom.drawRoom();

        // Verify that the correct rectangle is drawn based on the room size
        // For roomSize 1 (9x16), the rectangle coordinates should be (8, 8, 66, 66)
        // This is calculated from the formula: (width / 12, height / 12, width * 4 / 6, height * 4 / 6)
        // where width = 100, height = 100
        // So, (100 / 12, 100 / 12, 100 * 4 / 6, 100 * 4 / 6) = (8, 8, 66, 66)
        // The rectangle dimensions may vary depending on the actual clip bounds set by the Graphics object.
        assertEquals(new Rectangle(8, 8, 66, 66), ((MockGraphics) graphics).getDrawnRectangle());
    }

    // Mock Graphics class for testing
    private static class MockGraphics extends Graphics {
        private Rectangle drawnRectangle;

        @Override
        public Graphics create() {
            return null;
        }

        @Override
        public void translate(int x, int y) {

        }

        @Override
        public Color getColor() {
            return null;
        }

        @Override
        public void setColor(Color c) {

        }

        @Override
        public void setPaintMode() {

        }

        @Override
        public void setXORMode(Color c1) {

        }

        @Override
        public Font getFont() {
            return null;
        }

        @Override
        public void setFont(Font font) {

        }

        @Override
        public FontMetrics getFontMetrics(Font f) {
            return null;
        }

        @Override
        public Rectangle getClipBounds() {
            return null;
        }

        @Override
        public void clipRect(int x, int y, int width, int height) {

        }

        @Override
        public void setClip(int x, int y, int width, int height) {

        }

        @Override
        public Shape getClip() {
            return null;
        }

        @Override
        public void setClip(Shape clip) {

        }

        @Override
        public void copyArea(int x, int y, int width, int height, int dx, int dy) {

        }

        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {

        }

        @Override
        public void fillRect(int x, int y, int width, int height) {

        }

        @Override
        public void drawRect(int x, int y, int width, int height) {
            drawnRectangle = new Rectangle(x, y, width, height);
        }

        @Override
        public void clearRect(int x, int y, int width, int height) {

        }

        @Override
        public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

        }

        @Override
        public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

        }

        @Override
        public void drawOval(int x, int y, int width, int height) {

        }

        @Override
        public void fillOval(int x, int y, int width, int height) {

        }

        @Override
        public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

        }

        @Override
        public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

        }

        @Override
        public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

        }

        @Override
        public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

        }

        @Override
        public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

        }

        @Override
        public void drawString(String str, int x, int y) {

        }

        @Override
        public void drawString(AttributedCharacterIterator iterator, int x, int y) {

        }

        @Override
        public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
            return false;
        }

        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
            return false;
        }

        @Override
        public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
            return false;
        }

        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
            return false;
        }

        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
            return false;
        }

        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
            return false;
        }

        @Override
        public void dispose() {

        }

        public Rectangle getDrawnRectangle() {
            return drawnRectangle;
        }

        // Implement other abstract methods of the Graphics class if needed
        // They can be left empty for testing purposes
    }
}*/
