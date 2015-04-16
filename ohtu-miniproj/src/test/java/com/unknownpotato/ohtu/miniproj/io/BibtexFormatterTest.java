package com.unknownpotato.ohtu.miniproj.io;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;
import com.unknownpotato.ohtu.miniproj.domain.References;
import java.util.HashMap;

public class BibtexFormatterTest {

    Reference ref;

    @Before
    public void setUp() {
//        ReferenceFactory.setReferences(new References());
//        ref = ReferenceFactory.createBookReference("Victor Bankowski",
//                "a Dive into the Rust Programming Language",
//                "Unknownpotato publishing", "2051");
//        ref.setName("Bankowski2051");
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        ref = Reference.createReference(ReferenceType.BOOK, "Bankowski2051", fields);
    }

    @Test
    public void outputIsCorrectTest() {
    	String formatted = "";
    	try{
    		formatted = BibtexFormatter.convertReference(ref);
    	} catch (Exception e) {
    		fail("convertReference threw an exception: " + e);
    	}
        Scanner scanner = new Scanner(formatted);

        assertEquals("@Book{Bankowski2051,", scanner.nextLine());
        Set<String> lines = new HashSet<String>();

        lines.add("year = \"2051\",");
        lines.add("publisher = \"Unknownpotato publishing\",");
        lines.add("author = \"Victor Bankowski\",");
        lines.add("title = \"a Dive into the Rust Programming Language\",");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (lines.isEmpty()) {
                assertEquals("}", line);
                return;
            }
            if (lines.contains(line)) {
                lines.remove(line);
            } else {
                fail("Line was not expected: " + line);
            }
        }

        fail("Too few lines");
    }

}
