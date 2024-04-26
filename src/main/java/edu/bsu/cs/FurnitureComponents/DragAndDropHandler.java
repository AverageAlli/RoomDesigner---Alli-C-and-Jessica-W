package edu.bsu.cs.FurnitureComponents;
import edu.bsu.cs.ErrorHandling.Error;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.IOException;

public class DragAndDropHandler extends TransferHandler {

    private JPanel panel;
    private Component draggedComponent;

    public DragAndDropHandler(JPanel panel) {
        this.panel = panel;
        panel.setTransferHandler(this);
        panel.setDropTarget(new DropTarget(panel, DnDConstants.ACTION_MOVE, new MyDropTargetListener()));
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JLabel) {
            JLabel label = (JLabel) c;
            draggedComponent = label;
            return new Transferable() {
                @Override
                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[]{DataFlavor.imageFlavor};
                }

                @Override
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return flavor.equals(DataFlavor.imageFlavor);
                }

                @Override
                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                    if (flavor.equals(DataFlavor.imageFlavor)) {
                        return ((ImageIcon) label.getIcon()).getImage();
                    } else {
                        throw new UnsupportedFlavorException(flavor);
                    }
                }
            };
        }
        return null;
    }

    class MyDropTargetListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable transferable = dtde.getTransferable();
                if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    Image image = (Image) transferable.getTransferData(DataFlavor.imageFlavor);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel label = new JLabel(icon);
                    label.setLocation(dtde.getLocation());
                    panel.add(label);
                    panel.revalidate();
                    panel.repaint();
                    dtde.dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            } catch (IOException | UnsupportedFlavorException ex) {
                Error.showError("Error: Unsupported Image Flavor is trying to be dragged. See DragAndDropHandler Line 73.");
                dtde.rejectDrop();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            Point p = dtde.getLocation();
            if (draggedComponent != null) {
                draggedComponent.setLocation(p);
                draggedComponent.getParent().repaint();
            }
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            if (draggedComponent != null) {
                draggedComponent.getParent().remove(draggedComponent);
                draggedComponent.getParent().repaint();
                draggedComponent = null;
            }
        }
    }

    public void makeDraggable(Component component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedComponent = component;
                if (SwingUtilities.isRightMouseButton(e)) { // Check for right-click
                    panel.remove(component); // Remove the component on right-click
                    panel.revalidate();
                    panel.repaint();
                } else {
                    panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedComponent = null;
                panel.setCursor(Cursor.getDefaultCursor());
            }
        });

        component.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedComponent != null && SwingUtilities.isLeftMouseButton(e)) { // Only drag if left mouse button is pressed
                    Point p = SwingUtilities.convertPoint(component, e.getPoint(), panel);
                    draggedComponent.setLocation(p);
                    draggedComponent.getParent().repaint();
                }
            }
        });
    }
}