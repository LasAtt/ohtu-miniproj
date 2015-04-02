package com.unknownpotato.ohtu.miniproj.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;

public class BibtexFormatterTest {
	
	Reference ref;
	
	@Before
	public void setUp(){
		ref = ReferenceFactory.createBookReference("Victor Bankowski", "a Dive into the Rust Programming Language", "Unknownpotato publishing", "2051");
	}
	
	@Test
	public void test() {
		
	}

}
