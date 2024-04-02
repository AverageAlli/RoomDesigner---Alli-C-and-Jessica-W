package edu.bsu.cs;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import javax.swing.TransferHandler;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JComponent;



public class DragAndDropHandler extends MouseAdapter implements DragGestureListener {
    private JComponent targetComponent;

    public DragAndDropHandler(JComponent targetComponent) {
        this.targetComponent = targetComponent;
        DragSource ds = DragSource.getDefaultDragSource();
        ds.createDefaultDragGestureRecognizer(targetComponent, TransferHandler.COPY_OR_MOVE, this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TransferHandler handler = targetComponent.getTransferHandler();
        handler.exportAsDrag(targetComponent, e, TransferHandler.COPY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePressed(e);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        // Not needed for mouse handling, but required for the interface
    }


//private JPanel targetPanel; // Panel where the drop operation occurs
//
//    public DragAndDropHandler(JPanel targetPanel) {
//        this.targetPanel = targetPanel;
//        new DropTarget((Component) targetPanel, DnDConstants.ACTION_COPY, this);
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        JComponent component = (JComponent) e.getSource();
//        TransferHandler handler = component.getTransferHandler();
//        handler.exportAsDrag(component, e, TransferHandler.COPY);
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        mousePressed(e);
//    }
//
//    @Override
//    public void dragGestureRecognized(DragGestureEvent dge) {
//        // Not needed for mouse handling, but required for the interface
//    }
}
//    public DragAndDropHandler(JPanel roomPanel) {
//        this.roomPanel = roomPanel;
//        new DropTarget(roomPanel, DnDConstants.ACTION_COPY, this);
//        DragSource ds = DragSource.getDefaultDragSource();
//        ds.createDefaultDragGestureRecognizer(roomPanel, DnDConstants.ACTION_COPY, this);
//    }
//
//    @Override
//    public void drop(DropTargetDropEvent dtde) {
//        try {
//            Transferable tr = dtde.getTransferable();
//            Object data = tr.getTransferData(DataFlavor.javaFileListFlavor);
//            if (data instanceof java.util.List) {
//                // Handle the dropped files
//                java.util.List<?> fileList = (java.util.List<?>) data;
//                for (Object file : fileList) {
//                    // Process each dropped file
//                    System.out.println("Dropped file: " + file.toString());
//                }
//            }
//            dtde.dropComplete(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            dtde.rejectDrop();
//        }
//    }
//
//    @Override
//    public void dragGestureRecognized(DragGestureEvent dge) {
//        DragSource ds = dge.getDragSource();
//        ImageIcon icon = new ImageIcon("src/main/ObjectImages/bed.png");
//        ImageTransfer transferableImage = new ImageTransfer(icon.getImage());
//        ds.startDrag(dge, null, transferableImage, null);
//    }