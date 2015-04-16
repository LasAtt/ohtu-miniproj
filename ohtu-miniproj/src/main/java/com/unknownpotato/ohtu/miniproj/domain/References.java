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
            if(refs.get(0).getName().equals(name)){
                refs.remove(i);
            }
        }
    }
    
    public Reference getReference(String name){
        for (Reference ref : refs) {
            if(ref.getName().equals(name)){
                return ref;
            }
        }
        return null;
    }
    
    public List<Reference> getReferences() {
        return refs;
    }
}
