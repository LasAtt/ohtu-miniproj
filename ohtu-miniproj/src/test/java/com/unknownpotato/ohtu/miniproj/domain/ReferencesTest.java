
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferencesTest {

    public References refs;
    @Before
    public void setUp() {
        refs = new References();
    }

    @Test
    public void deleteReferenceWorksWithCorrectName() {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        refs.addReference(Reference.createReference(ReferenceType.BOOK, "joo98", fields));
        refs.deleteReference("joo98");
        assertEquals(null, refs.getReference("joo98"));
        assertEquals(0, refs.getReferences().size());
    }
}