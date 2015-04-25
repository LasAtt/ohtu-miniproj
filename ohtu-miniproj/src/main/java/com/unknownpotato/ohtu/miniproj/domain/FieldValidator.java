package com.unknownpotato.ohtu.miniproj.domain;

import org.codehaus.plexus.util.StringUtils;

/**
 * A class for validating the user's input to a given field.
 */
public class FieldValidator {
    
/**
     * Makes sure that the user's input to the specified field is valid.
     *
     * @param field the specified field
     * @param input the user's own input to the field
     */
    public static boolean validate(String field, String input) {
        switch (field) {
            case ("year"):
                return (StringUtils.isNumeric(input) && input.length() >= 3);
            case ("chapter"):
                return (StringUtils.isNumeric(input));
        }
        return true;
    }
}
