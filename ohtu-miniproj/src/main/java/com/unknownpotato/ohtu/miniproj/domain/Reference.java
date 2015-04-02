/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author axwikstr
 */
public class Reference {

    private Map<String, String> fields;
    private ReferenceType type;

    protected Reference(ReferenceType type) {
        this.type = type;
        this.fields = new HashMap();
    }

    protected void addField(String key, String value) {
        fields.put(key, value);
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public Set<String> getFields() {
        return fields.keySet();
    }

    public void editField(String key, String value) {
        if (!fields.containsKey(key)) {
            throw new NoSuchFieldError("Reference" + type + " does not have field " + key);
        } else {
            fields.put(key, value);
        }
    }
}
