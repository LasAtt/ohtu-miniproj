package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyjopy
 */
public class ReferenceUtilsTest {

    public ReferenceUtilsTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void nameGeneratorTakesValuesFromFieldsIfNoAuthorOrYearValuesExist() {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "");
        fields.put("title", "Title");
        fields.put("year", "1990");
        fields.put("publisher", "Publisher");
        fields.put("placeholder", "LongValue");
        Reference ref = Reference.createReference(ReferenceType.BOOK, "", fields);

        assertEquals("19PuLoTi", ref.getName());
    }
    
    @Test
    public void nameGeneratorAppendsRandomCharactersIfNameTooShortAfterGenerating() {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "");
        fields.put("title", "T");
        fields.put("year", "1");
        fields.put("publisher", "Pu");
        Reference ref = Reference.createReference(ReferenceType.BOOK, "", fields);
        System.out.println(ref.getName());
        assertFalse(ref.getName().equals("1TPu"));
    }

    @Test
    public void generateNameForReferenceIfReferenceNameEmpty() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("author", "Author");
        fields.put("title", "Title");
        fields.put("year", "1990");
        fields.put("publisher", "Publisher");
        Reference ref = Reference.createReference(ReferenceType.BOOK, "", fields);

        assertEquals("author90", ref.getName());
    }

    @Test
    public void DontGenerateNameForReferenceIfReferenceGiven() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("author", "Author");
        fields.put("title", "Title");
        fields.put("year", "1990");
        fields.put("publisher", "Publisher");
        Reference ref = Reference.createReference(ReferenceType.BOOK, "Coolname", fields);

        assertEquals("Coolname", ref.getName());
    }

}
