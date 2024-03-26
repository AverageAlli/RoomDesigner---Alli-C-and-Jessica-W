package edu.bsu.cs;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DragAndDropHandler extends DropTargetAdapter implements DragGestureListener {

    private JPanel targetPanel; // Panel where the drop operation occurs

    public DragAndDropHandler(JPanel targetPanel) {
        this.targetPanel = targetPanel;
        new DropTarget(targetPanel, DnDConstants.ACTION_COPY, this);
        DragSource ds = DragSource.getDefaultDragSource();
        ds.createDefaultDragGestureRecognizer(targetPanel, DnDConstants.ACTION_COPY, this);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable tr = dtde.getTransferable();
            Object data = tr.getTransferData(DataFlavor.javaFileListFlavor);
            if (data instanceof java.util.List) {
                // Handle the dropped files
                java.util.List<?> fileList = (java.util.List<?>) data;
                for (Object file : fileList) {
                    // Process each dropped file
                    System.out.println("Dropped file: " + file.toString());
                }
            }
            dtde.dropComplete(true);
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        DragSource ds = dge.getDragSource();
        ImageIcon icon = new ImageIcon("bed.png");
        ImageTransfer transferableImage = new ImageTransfer(icon.getImage());
        ds.startDrag(dge, null, transferableImage, null);
    }
}