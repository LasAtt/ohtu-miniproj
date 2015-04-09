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
    private String name;

    protected Reference(ReferenceType type) {
        this.type = type;
        this.fields = new HashMap();
    }

    public static Reference createReference(ReferenceType type, Map<String, String> fields) {
        Reference ref = new Reference(type);
        for (String f : type.getRequiredFields()) {
            ref.addField(f, fields.get(f));
        }
        for (String f : type.getOptionalFields()) {
            if (fields.containsKey(f)) {
                ref.addField(f, fields.get(f));
            }
        }
        return ref;
    }

    protected void addField(String key, String value) {
        fields.put(key, value);
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public Set<String> getFieldKeys() {
        return fields.keySet();
    }
    
    public ReferenceType getType() {
    	return type;
    }

    /**
     * Checks if map contains the field to edit and puts the new value if field
     * is found, else throws error.
     *
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

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
