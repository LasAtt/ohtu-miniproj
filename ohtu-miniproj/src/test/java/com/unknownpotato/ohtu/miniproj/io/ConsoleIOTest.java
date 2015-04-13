package com.unknownpotato.ohtu.miniproj.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author atte
 */
public class ConsoleIOTest {

    private ConsoleIO io;
    private InputStream stdin;
    private PrintStream stdout;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final String INPUT1 = "Test Input";
    private static final String INPUT2 = "12345";

    @Before
    public void setUp() {
        io = new ConsoleIO();
        stdin = System.in;
        stdout = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void lineIsReadFromInput() {
        System.setIn(new ByteArrayInputStream(INPUT1.getBytes()));
        assertEquals("Test Input", io.readLine("prompt"));
    }

    @Test
    public void promptIsAddedToPrintsWhenReadingLine() {
        System.setIn(new ByteArrayInputStream(INPUT1.getBytes()));
        io.readLine("prompt");
        assertTrue(io.getPrints().contains("prompt "));
    }
    
    @Test
    public void promptIsWrittenToOutputStream() {
        System.setIn(new ByteArrayInputStream(INPUT1.getBytes()));
        io.readLine("prompt");
        assertEquals("prompt ", outContent.toString());
    }

    @Test
    public void integerIsReadFromInput() {
        System.setIn(new ByteArrayInputStream(INPUT2.getBytes()));
        assertEquals(12345, io.readInt("prompt"));
    }

    @Test
    public void promptIsAddedToPrintsWhenReadingInt() {
        System.setIn(new ByteArrayInputStream(INPUT2.getBytes()));
        io.readInt("prompt");
        assertTrue(io.getPrints().contains("prompt "));
    }
    
    @Test
    public void promptIsWrittenToOutputStreamWhenReadingInt() {
        System.setIn(new ByteArrayInputStream(INPUT2.getBytes()));
        io.readInt("prompt");
        assertEquals("prompt ", outContent.toString());
    }

    @Test
    public void printWritesParameterToOutputCorrectly() {
        io.print(INPUT1);
        assertEquals(INPUT1, outContent.toString());
        assertTrue(io.getPrints().contains(INPUT1));
    }
    
    @Test
    public void printlnWritesParameterToOutputCorrectly() {
        io.println(INPUT1);
        assertEquals(INPUT1 + '\n', outContent.toString());
        assertTrue(io.getPrints().contains(INPUT1 + '\n'));
    }
    
    @After
    public void after() {
        System.setIn(stdin);
        System.setOut(stdout);
    }
}
