/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author axwikstr
 */
@Component
public class References {
    private List<Reference> refs;
    
    public References() {
        refs = new ArrayList();
    }
    
    public void addReference(Reference r) {
        refs.add(r);
    }
    
    public void deleteReference(String name){
        for (int i = 0; i < refs.size(); i++) {
            if(refs.get(i).getName().equals(name)){
                refs.remove(i);
            }
        }
    }
    
    public Reference getReference(String name){
        return refs.stream().filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    public List<Reference> getReferences() {
        return refs;
    }
}
