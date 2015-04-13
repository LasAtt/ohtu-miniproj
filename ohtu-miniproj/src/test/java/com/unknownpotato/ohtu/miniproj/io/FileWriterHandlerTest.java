package com.unknownpotato.ohtu.miniproj.io;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author atte
 */
public class FileWriterHandlerTest {

    File file;
    FileWriterHandler fw;
    Scanner scanner;

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        file = new File("test");
        fw = new FileWriterHandler(file);
        scanner = new Scanner(file);
    }

    @After
    public void after() {
        file.delete();
    }

    @Test
    public void fileIsCreatedUponConstruction() {
        assertTrue(file.exists());
    }
    
    @Test
    public void stringIsWrittenCorrectlyToWileWhenInitializedWithStringAsParameter() throws IOException {
        FileWriterHandler w = new FileWriterHandler("testy");
        Scanner s = new Scanner(new File("testy"));
        w.writeTo("test");
        assertEquals("test", s.nextLine());
    }

    @Test
    public void stringIsWrittenCorrectlyToFile() throws IOException {
        fw.writeTo("test");
        assertEquals("test", scanner.nextLine());
    }

    @Test
    public void listOfStringsIsWrittenCorrectlyToFile() throws IOException {
        List<String> strings = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
            strings.add("test" + i);
        fw.writeTo(strings);
        for (int i = 0; i < 10; i++)
            assertEquals("test" + i, scanner.nextLine());
    }
}
