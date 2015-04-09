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
public enum ReferenceType {

    BOOK(new String[]{"author", "title", "year", "publisher"}, new String[]{"volume", "number", "series", "address", "edition", "month", "note"}),
    ARTICLE(new String[]{"author", "title", "journal", "year", "volume"}, new String[]{"number", "pages", "month", "note", "key"});

    private final String[] requiredFields, optionalFields;

    private ReferenceType(String[] requiredFields, String[] optionalFields) {
        this.requiredFields = requiredFields;
        this.optionalFields = optionalFields;
    }

    public String[] getRequiredFields() {
        return requiredFields;
    }

    public String[] getOptionalFields() {
        return optionalFields;
    }
    
    

}
