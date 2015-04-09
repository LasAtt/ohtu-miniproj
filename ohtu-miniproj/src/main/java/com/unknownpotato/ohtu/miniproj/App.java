/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import com.unknownpotato.ohtu.miniproj.io.BibtexFormatter;

/**
 *
 * @author axwikstr
 */
public class App {
    public static void main(String[] args) {
    	Reference ref = ReferenceFactory.createBookReference("Victor Bankowski",
				"a Dive into the Rust Programming Language",
				"Unknownpotato publishing", "2051");
		ref.setName("Bankowski2051");
		System.out.println(BibtexFormatter.convertReference(ref));
    }
}
