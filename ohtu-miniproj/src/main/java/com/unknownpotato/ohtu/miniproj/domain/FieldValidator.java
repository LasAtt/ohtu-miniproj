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
                if (!StringUtils.isNumeric(input) || input.length() < 3) {
                    return false;
                }
            case ("chapter"):
                if (!StringUtils.isNumeric(input)) {
                    return false;
                }
        }
        return true;
    }
}
