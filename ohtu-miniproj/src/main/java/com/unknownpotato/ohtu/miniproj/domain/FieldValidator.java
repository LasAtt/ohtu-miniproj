package com.unknownpotato.ohtu.miniproj.domain;

import org.codehaus.plexus.util.StringUtils;

/**
 *
 * @author Teemu
 */
public class FieldValidator {

    public static boolean validate(String field, String input) {
        switch (field) {
            case ("year"):
                return (StringUtils.isNumeric(input) && input.length() > 3);
            case ("chapter"):
                return (StringUtils.isNumeric(input));
        }
        return true;
    }
}
