package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {
    @Test
    public void testShowError() {
        // Redirect System.err to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));

        // Call the method
        Error.showError("Test error message");

        // Verify the output
        assertEquals("Error: Test error message", outputStreamCaptor.toString().trim());
    }
}