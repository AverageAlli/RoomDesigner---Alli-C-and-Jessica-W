package edu.bsu.cs;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BedroomDesignerCLITest {

    @Test
    void testOpenBedroomDesignerGUI() {
        // Test opening GUI with valid dimensions
    }

    /*@Test
    void testExitProgram() {
        // Test exiting the program when 'e' is entered
        assertThrows(SecurityException.class, () -> provideInput("e", "10", "15"));
    }

    @Test
    void testInvalidInput() {
        // Test error handling for invalid input
        String[] args = { "10", "15" };
        provideInput("invalid", "10", "e");
        assertThrows(SecurityException.class, () -> BedroomDesignerCLI.main(args));
    }
*/
    private BedroomDesignerGUI openGUIWithDimensions(String length, String width) {
        provideInput(length, width);
        BedroomDesignerCLI.main(new String[]{});
        return null; // Return value not relevant for this test
    }

    private void provideInput(String... inputs) {
        String input = String.join(System.lineSeparator(), inputs);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
