/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author atte
 */
public final class ReferenceUtils {

    private ReferenceUtils() {
    }

    /**
     * Filters references by tag
     *
     * @param refs List of references to be filtered
     * @param tag Tag to filter by
     * @return Filtered list.
     */
    private List<Reference> filterByTag(List<Reference> refs, String tag) {
        return refs.stream()
                .filter(s -> s.getTags().contains(tag))
                .collect(Collectors.toList());
    }
    
    /**
     * Filters references by type
     * @param refs List of references to be filtered
     * @param type Tag to filter by
     * @return Filtered list
     */
    private List<Reference> filterByType(List<Reference> refs, ReferenceType type) {
        return refs.stream()
                .filter(s -> s.getType() == type)
                .collect(Collectors.toList());
    }
}
