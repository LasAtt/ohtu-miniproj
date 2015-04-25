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

    Reference BookRef;
    Reference ArticleRef;
    Reference InproceedingsRef;
    Reference UmlautRef;

    @Before
    public void setUp() {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        BookRef = Reference.createReference(ReferenceType.BOOK, "Bankowski2051", fields);
        
        fields = new HashMap<String, String>();
        fields.put("author", "Axel Wikstrom");
        fields.put("title", "how the term BINARY!!! is really stupid");
        fields.put("journal", "Some fukken IEEE thingy");
        fields.put("year", "2050");
        fields.put("volume", "12");
        ArticleRef = Reference.createReference(ReferenceType.ARTICLE, "Wikstrom2050", fields);
        
        fields = new HashMap<String, String>();
        fields.put("author", "Atte Lassila");
        fields.put("title", "Voxi tekee rumia testeja");
        fields.put("booktitle", "Kuinka tehda hyvat testit");
        fields.put("year", "2049");
        InproceedingsRef = Reference.createReference(ReferenceType.INPROCEEDINGS, "Lassila:49", fields);
        
        fields = new HashMap<String, String>();
        fields.put("author", "Victör Bankowski");
        fields.put("title", "a Dive intö the Rust Progrämming Languäge");
        fields.put("publisher", "Unknownpotatå publishing");
        fields.put("year", "2051");
        UmlautRef = Reference.createReference(ReferenceType.BOOK, "VBankowski2051", fields);
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
				lines.add("author = \"Axel Wikstrom\",");
				lines.add("title = \"how the term BINARY!!! is really stupid\",");
				lines.add("journal = \"Some fukken IEEE thingy\",");
				lines.add("year = \"2050\",");
				lines.add("volume = \"12\",");
				break;
			case INPROCEEDINGS:
				lines.add("author = \"Atte Lassila\",");
				lines.add("title = \"Voxi tekee rumia testeja\",");
				lines.add("booktitle = \"Kuinka tehda hyvat testit\",");
				lines.add("year = \"2049\",");
				break;
		}

        
		return lines;
	}

    private Set<String> createUmlautCompareSet() {
    	Set<String> lines = new HashSet<String>();
    	lines.add("year = \"2051\",");
        lines.add("publisher = \"Unknownpotat{\\aa} publishing\",");
        lines.add("author = \"Vict{\\\"o}r Bankowski\",");
        lines.add("title = \"a Dive int{\\\"o} the Rust Progr{\\\"a}mming Langu{\\\"a}ge\",");
        return lines;
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
    public void bookReferenceOutputIsCorrectWithMandatoryFieldsTest() {
    	String formatted = BibtexFormatter.convertReference(BookRef);
        Scanner scanner = new Scanner(formatted);

        assertEquals("@Book{Bankowski2051,", scanner.nextLine());
        Set<String> lines = createCompareSet(BookRef.getType());

        analyzeLines(scanner, lines);
    }	
    
    @Test
    public void articleReferenceOutputIsCorrectWithMandatoryFieldsTest() {
    	String formatted = BibtexFormatter.convertReference(ArticleRef);
        Scanner scanner = new Scanner(formatted);

        assertEquals("@Article{Wikstrom2050,", scanner.nextLine());
        Set<String> lines = createCompareSet(ArticleRef.getType());

        analyzeLines(scanner, lines);
    }
    
    @Test
    public void inproceedingsReferenceOutputIsCorrectWithMandatoryFieldsTest() {
    	String formatted = BibtexFormatter.convertReference(InproceedingsRef);
    	Scanner scanner = new Scanner(formatted);
    	
    	assertEquals("@Inproceedings{Lassila:49,", scanner.nextLine());
    	Set<String> lines = createCompareSet(InproceedingsRef.getType());
    	
    	analyzeLines(scanner, lines);
    }
    
    @Test
    public void umlautReferenceIsCorrect() {
    	String formatted = BibtexFormatter.convertReference(UmlautRef);
    	Scanner scanner = new Scanner(formatted);
    	
    	assertEquals("@Book{VBankowski2051,", scanner.nextLine());
    	Set<String> lines = createUmlautCompareSet();
    	
    	analyzeLines(scanner, lines);
    }
    
    @Test
    public void umlautsAreReplacedWhenStringContainsThem(){
    	String escaped = BibtexFormatter.replaceUmlauts("jääkökkä åmena");
    	assertEquals("j{\\\"a}{\\\"a}k{\\\"o}kk{\\\"a} {\\aa}mena", escaped);	
    }
    
    @Test
    public void nothingIsDoneToaStringIfThereAreNoUmlauts(){
    	String nonumlauted = "this string contains no umlauts";
    	String escaped = BibtexFormatter.replaceUmlauts(nonumlauted);
    	assertEquals(nonumlauted, escaped);
    	assertEquals("this string contains no umlauts", escaped);
    }

}
