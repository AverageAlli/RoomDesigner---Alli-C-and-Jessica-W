package edu.bsu.cs;

import edu.bsu.cs.GUI.BedroomDesignerStart;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BedroomDesignerStartTest {

    @Test
    public void testStart() {
        // Create a mock instance of BedroomDesignerStart
        BedroomDesignerStart designerStart = mock(BedroomDesignerStart.class);
        designerStart.start();

        // Verify that the start() method is called
        verify(designerStart, times(1)).start();
    }

    @Test
    public void testOpenRoomDesignerGUI() {
        // Create a mock instance of BedroomDesignerStart
        BedroomDesignerStart designerStart = mock(BedroomDesignerStart.class);
        designerStart.openRoomDesignerGUI(10.0, 8.0);

        // Verify that the method is called with the specified arguments
        verify(designerStart, times(1)).openRoomDesignerGUI(10.0, 8.0);
    }
}
