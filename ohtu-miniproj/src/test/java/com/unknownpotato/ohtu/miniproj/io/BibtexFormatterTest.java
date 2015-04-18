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
    
    private Set<String> createCompareSet(ReferenceType type) {
		Set<String> lines = new HashSet<String>();
		
		switch(type){
			case BOOK:
				lines.add("year = \"2051\",");
		        lines.add("publisher = \"Unknownpotato publishing\",");
		        lines.add("author = \"Victor Bankowski\",");
		        lines.add("title = \"a Dive into the Rust Programming Language\",");
		        break;
			case ARTICLE:
				break;
		}

        
		return lines;
	}

    @Test
    public void bookReferenceOutputIsCorrectTest() {
    	String formatted = BibtexFormatter.convertReference(ref);
        Scanner scanner = new Scanner(formatted);

        assertEquals("@Book{Bankowski2051,", scanner.nextLine());
        Set<String> lines = createCompareSet(ref.getType());

        analyzeLines(scanner, lines);
    }

	private void analyzeLines(Scanner scanner, Set<String> lines) {
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

	
    
    @Test
    public void articleReferenceOutputIsCorrectTest() {
    	String formatted = BibtexFormatter.convertReference(ref);
        Scanner scanner = new Scanner(formatted);

        assertEquals("@Article{Bankowski2051,", scanner.nextLine());
        Set<String> lines = createCompareSet(ref.getType());

        analyzeLines(scanner, lines);
    }

}
