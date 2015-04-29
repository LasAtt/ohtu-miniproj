
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferencesTest {

    public References refs;
    public Reference ref, articleRef;
    @Before
    public void setUp() {
        refs = new References();
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        
        ref = Reference.createReference(ReferenceType.BOOK, "joo98", fields);
        
        fields = new HashMap<String, String>();
        fields.put("author", "Axel Wikström");
        fields.put("title", "how the term BINARY!!! is really stupid");
        fields.put("journal", "Some fukken IEEE thingy");
        fields.put("year", "2050");
        fields.put("volume", "12");
        articleRef = Reference.createReference(ReferenceType.ARTICLE, "Wikström2050", fields);
    }

    @Test
    public void deleteReferenceWorksWithCorrectName() {
        refs.addReference(ref);
        refs.deleteReference("joo98");
        assertEquals(null, refs.getReference("joo98"));
        assertEquals(0, refs.getReferences().size());
    }
    
    @Test
    public void addReferenceWorks(){
        refs.addReference(ref);
        assertEquals(ref, refs.getReference("joo98"));
        assertEquals(1, refs.getReferences().size());
    }
    
    @Test
    public void getReferenceGivesNullForNonexistentReference(){
        refs.addReference(ref);
        assertEquals(null, refs.getReference("nonexistent"));
    }
    
    @Test
    public void typeFilterTest() {
        refs.addReference(ref);
        refs.addReference(articleRef);
        refs.addFilter(ReferenceFilters.isOfType(ReferenceType.BOOK));
        
        assertEquals(1, refs.getReferences().size());
        
        refs.clearFilters();
        
        assertEquals(2, refs.getReferences().size());
    }
    
    @Test
    public void tagFilterTest() {
        refs.addReference(ref);
        refs.addReference(articleRef);
        articleRef.addTag("binary");
        refs.addFilter(ReferenceFilters.hasTag("binary"));
        
        assertEquals(1, refs.getReferences().size());
        assertTrue(refs.getReferences().contains(articleRef));
        assertTrue(!refs.getReferences().contains(ref));
    }
    
    @Test
    public void fieldFilterTest() {
        refs.addReference(ref);
        refs.addReference(articleRef);
        
        refs.addFilter(ReferenceFilters.fieldContains("title","BINARY"));
        
        assertEquals(1, refs.getReferences().size());
        assertTrue(refs.getReferences().contains(articleRef));
        assertTrue(!refs.getReferences().contains(ref));
    }
    
    @Test
    public void multipleFiltersTest() {
        refs.addReference(ref);
        refs.addReference(articleRef);
        articleRef.addTag("binary");
        refs.addFilter(ReferenceFilters.hasTag("binary"));
        refs.addFilter(ReferenceFilters.isOfType(ReferenceType.ARTICLE));
        
        assertEquals(1, refs.getReferences().size());
        assertTrue(refs.getReferences().contains(articleRef));
        assertTrue(!refs.getReferences().contains(ref));
        
        refs.addFilter(ReferenceFilters.hasTag("asd"));
        
        assertTrue(refs.getReferences().isEmpty());
        
        refs.clearFilters();
        
        assertEquals(2, refs.getReferences().size());
    }
}