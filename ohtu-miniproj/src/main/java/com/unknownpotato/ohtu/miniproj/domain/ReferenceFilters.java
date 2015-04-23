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
public class ReferenceFilters{
    public static Predicate<Reference> isType(ReferenceType type) {
        return r -> r.getType() == type;
    }
    
    public static Predicate<Reference> hasTag(String tag) {
        return r -> r.getTags().contains(tag);
    }
    
}

