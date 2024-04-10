package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import javax.swing.JPanel;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DropTargetDropEvent;

import static org.mockito.Mockito.*;

public class DragAndDropHandlerTest {

    @Test
    public void testDrop() {
        // Create a mock JPanel for the targetPanel
        JPanel mockPanel = mock(JPanel.class);

        // Create a DropTargetDropEvent with a mock Transferable
        DropTargetDropEvent mockDropEvent = mock(DropTargetDropEvent.class);
        when(mockDropEvent.getTransferable()).thenReturn(mock(Transferable.class));

        // Create an instance of DragAndDropHandler
        DragAndDropHandler handler = new DragAndDropHandler(mockPanel);

        // Call the drop method with the mock DropTargetDropEvent
        handler.drop(mockDropEvent);

        // Verify that dtde.dropComplete(true) is called
        verify(mockDropEvent).dropComplete(true);
    }

    @Test
    public void testDragGestureRecognized() {
        // Create a mock DragGestureEvent
        DragGestureEvent mockDragEvent = mock(DragGestureEvent.class);

        // Create an instance of DragAndDropHandler
        DragAndDropHandler handler = new DragAndDropHandler(new JPanel());

        // Call the dragGestureRecognized method with the mock DragGestureEvent
        handler.dragGestureRecognized(mockDragEvent);

        // It's difficult to directly test the dragGestureRecognized method since it involves GUI interactions.
        // However, you can verify that DragSource.startDrag is called.
        // This assumes that the DragSource behavior is correct and doesn't need to be tested separately.
        verify(mockDragEvent.getDragSource()).startDrag(eq(mockDragEvent), isNull(), any(ImageTransfer.class), isNull());
    }
}
