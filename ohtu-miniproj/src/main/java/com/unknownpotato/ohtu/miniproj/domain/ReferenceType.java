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

    BOOK(new String[]{"author", "title", "year", "publisher"},
            new String[]{"volume", "number", "series", "address", "edition", "month", "note"}),
    ARTICLE(new String[]{"author", "title", "journal", "year", "volume"},
            new String[]{"number", "pages", "month", "note", "key"}),
    INPROCEEDINGS(new String[]{"author", "title", "booktitle"},
            new String[]{"editor", "volume/number", "series", "pages", "address", "month", "organization", "publisher", "note", "key"}),
    BOOKLET(new String[]{"title"},
            new String[]{"author", "howpublished", "address", "month", "year", "note", "key"}),
    INSTITUTION(new String[]{"title"},
            new String[]{"author", "howpublished"}),
    CONFERENCE(new String[]{"author", "title", "booktitle"},
            new String[]{"editor", "volume/number", "series", "pages", "address", "month", "organization", "publisher", "note", "key"}),
    INBOOK(new String[]{"author/editor", "title", "chapter/pages", "publisher", "year"},
            new String[]{"volume/number", "series", "type", "address", "edition", "month", "note", "key"}),
    INCOLLECTION(new String[]{"author", "title", "booktitle", "publisher", "year"},
            new String[]{"editor", "volume/number", "series", "type", "chapter", "pages", "address", "edition", "month", "note", "key"}),
    MANUAL(new String[]{"title"},
            new String[]{"author", "organization"});

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

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

}
