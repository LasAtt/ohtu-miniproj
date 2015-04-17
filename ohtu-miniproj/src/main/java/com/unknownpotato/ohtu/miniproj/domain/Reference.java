package com.unknownpotato.ohtu.miniproj.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Reference class that includes the type of reference and all necessary fields.
 */
public class Reference {

    private Map<String, String> fields;
    private ReferenceType type;
    private String name;
    private Set<String> tags;

    private Reference(ReferenceType type, String name) {
        this.name = name;
        this.type = type;
        this.fields = new HashMap();
        this.tags = new TreeSet<>();
    }

    private static String generateReferenceName(String name, Map<String, String> fields) {
        String generatedName = fields.get("author");
        generatedName = generatedName.substring(generatedName.lastIndexOf(" ") + 1);
        generatedName = generatedName.toLowerCase();
        String year = fields.get("year");
        generatedName += year.substring(2);
        return generatedName;
    }

    public static Reference createReference(ReferenceType type, String name, Map<String, String> fields) {
        String newName = name;
        if(name.isEmpty()){
            newName = generateReferenceName(name, fields);
        }
        Reference ref = new Reference(type, newName);
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
    
    public void addTag(String tag) {
        tags.add(tag);
    }
    
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public Set<String> getTags() {
        return tags;
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

    public Map<String, String> getFields() {
        return fields;
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

    public void setName(String name) {
        this.name = name;
    }
}
