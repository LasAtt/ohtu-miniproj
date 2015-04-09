package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyjopy
 */
public class ReferenceTest {

    public Reference ref;
    public References refs;
    public ReferenceFactory reffactor;

    public ReferenceTest() {
        refs = new References();
        reffactor = new ReferenceFactory(refs);
    }

    @Before
    public void setUp() {
        ref = ReferenceFactory.createBookReference("Author", "Title", "Year", "Publisher");
    }

    /**
     * Tests if editing nonexistence field returns error.
     */
    @Test
    public void editFieldReturnsErrorForNonexistentField() {
        try {
            ref.editField("NonexistentField", "testinput");
        } catch (NoSuchFieldError e) {
        }
    }
    
    @Test
    public void addingNewFieldWithValueWorks(){
        ref.addField("NewField", "NewValue");
        assertEquals(ref.getField("NewField"), "NewValue");
    }
    
    @Test
    public void addingFieldWithExistingValueReturnsError(){
        ref.addField("NewField", "NewValue");
        try{
            ref.addField("NewField", "AnotherValue");
        } catch (Error e){
            
        }
    }
    
    @Test
    public void getFieldReturnsRightField(){
        ref.addField("NewField", "NewValue");
        assertEquals("NewValue", ref.getField("NewField"));
    }
    
    @Test
    public void getFieldSetReturnsRightSet(){
        Set set = ref.getFieldKeys();
        assertEquals(11, set.size());
        assertTrue(set.contains("author"));
        assertTrue(set.contains("title"));
    }
            

}
