/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;
import com.unknownpotato.ohtu.miniproj.domain.References;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axwikstr
 */
public class JSONIOTest {

    public JSONIOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    References refs;

    @Before
    public void setUp() {
        HashMap<String, String> fields = new HashMap<String, String>();
        fields.put("author", "Victor Bankowski");
        fields.put("title", "a Dive into the Rust Programming Language");
        fields.put("publisher", "Unknownpotato publishing");
        fields.put("year", "2051");
        Reference ref = Reference.createReference(ReferenceType.BOOK, "Bankowski2051", fields);
        ref.addTag("asd");
        ref.addTag("cool");
        refs = new References();
        refs.addReference(ref);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void writeTest() {
        JSONWriter.saveReferences(refs, "test.json");
    }

    @Test
    public void readTest() {
        References loadedRefs = JSONReader.loadReferences("test.json");
        Assert.assertTrue(!loadedRefs.getReferences().isEmpty());
        Reference ban = loadedRefs.getReference("Bankowski2051");
        Assert.assertTrue(ban.getField("author").equals("Victor Bankowski"));
        Assert.assertTrue(ban.getType() == ReferenceType.BOOK);

        assertTrue(ban.getTags().size() == 2);
        assertTrue(ban.getTags().containsAll(Arrays.asList(new String[]{"asd", "cool"})));
    }
}
