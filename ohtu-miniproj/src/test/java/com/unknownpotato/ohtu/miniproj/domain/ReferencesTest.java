
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferencesTest {

    public References refs;
    public Reference ref;
    @Before
    public void setUp() {
        refs = new References();
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        ref = Reference.createReference(ReferenceType.BOOK, "joo98", fields);
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
}