/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.function.Predicate;

/**
 *
 * @author axwikstr
 */
public class ReferenceFilters {

    public static Predicate<Reference> isOfType(ReferenceType type) {
        return new Predicate<Reference>() {

            @Override
            public boolean test(Reference r) {
                return r.getType() == type;
            }

            @Override
            public String toString() {
                return "Is of type " + type.toString();
            }
        };
    }

    public static Predicate<Reference> hasTag(String tag) {
        return new Predicate<Reference>() {

            @Override
            public boolean test(Reference r) {
                return r.getTags().contains(tag);
            }

            @Override
            public String toString() {
                return "Has tag " + tag;
            }
        };
    }

    public static Predicate<Reference> fieldContains(String field, String value) {
        return new Predicate<Reference>() {

            @Override
            public boolean test(Reference r) {
                if(r.getFieldKeys().contains(field)) {
                    return r.getField(field).contains(value);
                }
                return false;
            }

            @Override
            public String toString() {
                return field + " contains " + value;
            }
        };
    }

}
