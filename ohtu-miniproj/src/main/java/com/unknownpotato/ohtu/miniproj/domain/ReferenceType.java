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

    BOOK({"author", "title", "year", "publisher"}, { "volume", "number", "series", "address", "edition", "month", "note" }),
    ARTICLE({"author", "title","journal", "year", "volume"}, {"number","pages","month","note","key"});
    
    private final String[] requiredFields, optionalFields;

    private ReferenceType(String[] requiredFields, String[] optionalFields) {
        this.requiredFields = requiredFields;
        this.optionalFields = optionalFields;
    }
    
    
}
