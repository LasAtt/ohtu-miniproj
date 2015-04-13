package com.unknownpotato.ohtu.miniproj.io;

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
    
    ConsoleIO io;   
    Scanner scanner;
    InputStream in;
    PrintStream out;
    
    @Before
    public void setUp() {
        in = mock(InputStream.class);
        out = mock(PrintStream.class);
    }
    /**
    @Test
    public void lineIsReadFromInput() {
        io.readLine("prompt");
    }
    
    @Test
    public void promptIsWrittenToPrintsWhenReadingLine() {
        when(scanner.nextLine()).thenReturn("test");
        io.readLine("prompt");
        assertTrue(io.getPrints().contains("prompt "));
    }
    
    @Test
    public void integerIsReadFromInput() {
        when(scanner.nextLine()).thenReturn("1");
        assertEquals(1, io.readInt("prompt"));
    }
    
    @Test
    public void promptIsWrittenToPrintsWhenReadingInt() {
        when(scanner.nextLine()).thenReturn("test");
        io.readInt("prompt");
        assertTrue(io.getPrints().contains("prompt "));
    }
    **/
}
