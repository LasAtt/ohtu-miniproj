/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

/**
 *
 * @author axwikstr
 */
public class ReferenceFactory {
    public static Reference createBookReference(String author, String title, String publisher, String year){
        Reference ref = new Reference(ReferenceType.BOOK);
        ref.addField("author", author);
        ref.addField("title", title);
        ref.addField("year", year);
        String[] optionalFields = { "volume", "number", "series", "address", "edition", "month", "note" };
        for (String optionalField : optionalFields) {
            ref.addField(optionalField, "");
        }
        return ref;
    }
}
