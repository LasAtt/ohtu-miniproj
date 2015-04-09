/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides methods for creating reference-classes with varying parameters.
 */
@Component
public class ReferenceFactory {
    
    private static References references;
    
    @Autowired
    public ReferenceFactory(References references) {
        this.references = references;
    }
    
    /**
     * Creates and returns a book reference with required fields. Reference has empty fields for optional fields.
     * @param author
     * @param title
     * @param publisher
     * @param year
     * @return 
     */
    public static Reference createBookReference(String author, String title, String publisher, String year){
        Reference ref = new Reference(ReferenceType.BOOK);
        ref.addField("author", author);
        ref.addField("title", title);
        ref.addField("year", year);
        ref.addField("publisher", publisher);
        String[] optionalFields = { "volume", "number", "series", "address", "edition", "month", "note" };
        for (String optionalField : optionalFields) {
            ref.addField(optionalField, "");
        }
        references.addReference(ref);
        return ref;
    }
}
