/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 *
 * @author axwikstr
 */
@Component
public class References {
    private List<Reference> refs;
    private List<Predicate<Reference>> filters;
    
    public References() {
        refs = new ArrayList();
        filters = new ArrayList();
    }
    
    public void addReference(Reference r) {
        refs.add(r);
    }
    
    public boolean deleteReference(String name){
        for (int i = 0; i < refs.size(); i++) {
            if(refs.get(i).getName().equals(name)){
                refs.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Reference getReference(String name) {
        return refs.stream().filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Reference> getReferences() {
        Stream<Reference> s = refs.stream();
        for (Predicate<Reference> filter : filters) {
            s = s.filter(filter);
        }
        return s.collect(Collectors.toList());
    }
    
    public void addFilter(Predicate<Reference> f) {
        filters.add(f);
    }
    
    public List<Predicate<Reference>> getFilters() {
        return filters;
    }
    
    public void clearFilters() {
        filters.clear();
    }
    
    public boolean contains(String name) {
        return refs.stream().anyMatch(r -> r.getName().equals(name));
    }
}
