/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;

/**
 *
 * @author axwikstr, VoxWave
 */
public class BibtexFormatter {
	
    public static String convertReference(Reference ref){
    	BibtexFormatter formatter = new BibtexFormatter();
    	ReferenceType type = ref.getType();
    	
    	switch (type) {
    		case BOOK:
    			return formatter.formatBook(ref);
    			
    	}
    	
		return null;    	
    }

	private String formatBook(Reference ref) {
		StringBuilder builder = new StringBuilder();
		builder.append("@Book{");
		builder.append(ref.getName());
		builder.append(",\n");
		String value = "";
		for (String field: ref.getFieldKeys()){
			value = ref.getField(field);
			if(value!=""){
				builder.append(field);
				builder.append(" = \"");
				builder.append(value);
				builder.append("\",\n");
			}
		}
			builder.append("}\n");
		 return builder.toString();
	}
}
