/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teemu
 */
public class FieldValidatorTest {

    @Before
    public void setUp() {
    }

    @Test
    public void nonNumericInputNotAcceptedWhenNumericInputRequired() {
        boolean result = FieldValidator.validate("year", "199S");
        assertTrue(!result);
        boolean result2 = FieldValidator.validate("chapter", "199S");
        assertTrue(!result2);
    }
    
    @Test
    public void yearInputNotAcceptedIfTooShort() {
        boolean result = FieldValidator.validate("year", "19");
        assertTrue(!result);
    }
}
