package com.unknownpotato.ohtu.miniproj.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;

public class BibtexFormatterTest {

	Reference ref;

	@Before
	public void setUp() {
		ref = ReferenceFactory.createBookReference("Victor Bankowski",
				"a Dive into the Rust Programming Language",
				"Unknownpotato publishing", "2051");
		ref.setName("Bankowski2051");
	}

	@Test
	public void test() {
		String formatted = BibtexFormatter.convertReference(ref);
		assertTrue(formatted.contains("@Book{Bankowski2051,\n")
				&& formatted.contains("author = \"Victor Bankowski\",\n")
				&& formatted.contains("title = \"a Dive into the Rust Programming Language\",\n")
				&& formatted.contains("year = \"2051\",\n") 
				&& formatted.contains("publisher = \"Unknownpotato publishing\",\n")
				);
	}

}
