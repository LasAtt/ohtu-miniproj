package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Reference class that includes the type of reference and all necessary fields.
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

    public Set<String> getField() {
        return fields.keySet();
    }

    /**
     * Checks if map contains the field to edit and puts the new value if field is found,
     * else throws error.
     * @param key
     * @param value 
     */
    public void editField(String key, String value) {
        if (!fields.containsKey(key)) {
            throw new NoSuchFieldError("Reference" + type + " does not have field " + key);
        } else {
            fields.put(key, value);
        }
    }
}
