package edu.bsu.cs;
import edu.bsu.cs.ErrorHandling.Error;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorTest {

    @Test
    public void testShowError() {
        // Redirect system error output for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        // Call the showError method
        Error.showError("Test Error Message");

        // Get the printed error message
        String printedMessage = outputStream.toString().trim();

        // Assert that the printed message matches the expected message
        assertEquals("Error: Test Error Message", printedMessage);
    }
}