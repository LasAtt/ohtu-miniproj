/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.domain;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author atte
 */
public final class ReferenceUtils {

    private ReferenceUtils() {
    }

    /**
     * Generates name for reference in according to it's fields
     *
     * @param name
     * @param fields
     * @return
     */
    public static String generateReferenceName(String name, Map<String, String> fields) {
        if (fields.get("author") != null && fields.get("year") != null
                && !fields.get("author").isEmpty() && !fields.get("year").isEmpty()) {
            return generateNameFromAuthorAndYear(fields);
        } else {
            return generateFailSafeName(fields);
        }
    }

    /**
     * Generates name from remaining field values if author or year fields are
     * empty.
     *
     * @return
     */
    private static String generateFailSafeName(Map<String, String> fields) {
        Random r = new Random();
        String generatedName = "";

        // append two chars from each field until maximum length
        for (String value : fields.values()) {
            if (value != null && generatedName.length() < 8) {
                generatedName += value.substring(0, Math.min(value.length(), 2));
            }
        }
        // append random chars to get length for name
        while (generatedName.length() < 5) {
            generatedName += (char) (r.nextInt(26) + 'a');
        }

        return removeOddCharactersFromString(generatedName);
    }

    /**
     * generates a name for a reference from its author and publishing year.
     * @param fields
     * @return 
     */
    private static String generateNameFromAuthorAndYear(Map<String, String> fields) {
        String generatedName = fields.get("author");

        generatedName = generatedName.substring(generatedName.lastIndexOf(" ") + 1);
        generatedName = generatedName.toLowerCase();
        generatedName += fields.get("year").substring(Math.min(2, fields.get("year").length()));

        return removeOddCharactersFromString(generatedName);
    }

    private static String removeOddCharactersFromString(String input) {
        //replace all lower Umlauts
     String result = input
             .replaceAll("ü", "ue")
             .replaceAll("ö", "o")
             .replaceAll("ä", "ae")
             .replaceAll("ß", "ss")
             .replaceAll("å", "a");
 
     //first replace all capital umlaute in a non-capitalized context (e.g. Übung)
     result = result
             .replaceAll("Ü(?=[a-zäöüß ])", "Ue")
             .replaceAll("Ö(?=[a-zäöüß ])", "O")
             .replaceAll("Ä(?=[a-zäöüß ])", "Ae");
 
     //now replace all the other capital umlaute
     result = result
             .replaceAll("Ü", "UE")
             .replaceAll("Ö", "O")
             .replaceAll("Ä", "AE")
             .replaceAll("Å", "A");
 
     return result;
    }

    /**
     * Reads Reference information to String and returns it.
     *
     * @param r reference to be converted
     * @return reference given as String representation.
     */
    public static String referenceToString(Reference r) {
        StringBuilder sb = new StringBuilder();
        sb.append(r.getType()).append(" - ").append(r.getName()).append(": [");
        r.getFieldKeys().stream()
                .forEach(f -> sb.append(f)
                        .append(": ")
                        .append(r.getField(f))
                        .append(" "));
        sb.append("]");
        if (!r.getTags().isEmpty()) {
            sb.append("\ntags: ");
            r.getTags().stream().forEach(t -> {
                sb.append(t).append(" ");
            });
        }
        return sb.toString();
    }

}
