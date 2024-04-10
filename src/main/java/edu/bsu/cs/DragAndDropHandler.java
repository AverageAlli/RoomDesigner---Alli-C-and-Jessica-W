package edu.bsu.cs;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import javax.swing.*;


public class DragAndDropHandler extends TransferHandler {
    private Component sourceComponent;

    public DragAndDropHandler(JPanel panel) {
        panel.setTransferHandler(this);
        panel.setDropTarget(new DropTarget(panel, DnDConstants.ACTION_MOVE, new DropTargetAdapter() {
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


                        if (sourceComponent != null && sourceComponent.getParent() != panel) {
                            Container sourceParent = sourceComponent.getParent();
                            sourceParent.remove(sourceComponent);
                            sourceParent.revalidate();
                            sourceParent.repaint();
                        }

                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (IOException | UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                    dtde.rejectDrop();
                }
            }
        }));
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        sourceComponent = c;
        JLabel label = (JLabel) c;
        ImageIcon icon = (ImageIcon) label.getIcon();
        Image image = icon.getImage();
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
                    return image;
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }
        };
    }
}

