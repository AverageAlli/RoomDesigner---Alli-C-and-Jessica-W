package edu.bsu.cs;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.IOException;

public class DragAndDropHandler extends TransferHandler {

    private JPanel panel;
    private JLabel draggedLabel;

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
        draggedLabel = (JLabel) c;
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
                    return ((ImageIcon) draggedLabel.getIcon()).getImage();
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }
        };
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
                ex.printStackTrace();
                dtde.rejectDrop();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            Component c = dtde.getDropTargetContext().getComponent();
            if (c instanceof JLabel && c.getParent() == panel) {
                draggedLabel = (JLabel) c;
                panel.remove(draggedLabel);
                panel.revalidate();
                panel.repaint();
                dtde.acceptDrag(DnDConstants.ACTION_MOVE);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            Point p = dtde.getLocation();
            draggedLabel.setLocation(p);
            panel.add(draggedLabel);
            panel.revalidate();
            panel.repaint();
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            panel.add(draggedLabel);
            panel.revalidate();
            panel.repaint();
        }
    }
}