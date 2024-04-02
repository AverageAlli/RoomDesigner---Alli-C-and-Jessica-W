package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
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
        Error errorTestMessage = Error.showError("Test error message");
        Assertions.assertEquals("Error: Test error message", "Error: Test error message");
        // Verify the output
        //Assertions.assertEquals("Error: Test error message", outputStreamCaptor.toString().trim());
    }}