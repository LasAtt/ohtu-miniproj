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
    
    public List<Reference> getReferences() {
        return refs;
    }
}
