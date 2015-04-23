package com.unknownpotato.ohtu.miniproj.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Reference class that includes the type of reference and all necessary fields.
 */

public class Reference {

    private Map<String, String> fields;
    private ReferenceType type;
    /**
     * The name used to identify the reference in the bibtex format. Must be unique.
     */
    private String name; 
    private Set<String> tags;

    private Reference(ReferenceType type, String name) {
        this.name = name;
        this.type = type;
        this.fields = new HashMap();
        this.tags = new LinkedHashSet<>();
    }
/**
 * Generates a name for the Reference containing the last name of the author and the year.
 * 
 * @param name
 * @param fields
 * @return Generated name used to identify the reference in the bibtex format.
 */
    private static String generateReferenceName(String name, Map<String, String> fields) {
        String generatedName = "";
        if (fields.get("author") != null && fields.get("year") != null) {
            generatedName = fields.get("author");
            generatedName = generatedName.substring(generatedName.lastIndexOf(" ") + 1);
            generatedName = generatedName.toLowerCase();
            String year = fields.get("year");
            generatedName += year.substring(2);
        } else {
            for (String value : fields.values()) {
                if(value != null){
                    generatedName += value.substring(0, Math.min(value.length(), 2));
                }
                if(generatedName.length() > 8){
                    break;
                }
            }
        }

        return generatedName;
    }

    /**
     * Creates a new Reference. It only adds the fields that are defined in the ReferenceType.
     * @param type
     * @param name If empty, a name is generated.
     * @param fields Map containing the field name as key.
     * @return
     */
    public static Reference createReference(ReferenceType type, String name, Map<String, String> fields) {
        String newName = name;
        if (name.isEmpty()) {
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
        tags.add(tag.trim().toLowerCase());
    }
    
    public void addTag(String... tags) {
        Arrays.asList(tags).stream().forEach(t -> {
            addTag(t);
        });
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }
    
    public void removeTag(String... tags) {
        Arrays.asList(tags).stream().forEach(t -> {
            removeTag(t);
        });
    }

    public Set<String> getTags() {
        return tags;
    }
    /**
     * Adds a field to the reference. Does not check that the ReferenceType has a field with key.
     * @param key
     * @param value
     */
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
     * @return 
     */
    public boolean editField(String key, String value) {
        if (!fields.containsKey(key)) {
            return false;
        } else {
            fields.put(key, value);
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
