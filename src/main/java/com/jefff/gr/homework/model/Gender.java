package com.jefff.gr.homework.model;

import static com.jefff.gr.homework.utility.StringUtility.isEmpty;

public enum Gender {
    Male,
    Female;

    public static Gender toEnum(String str) {
        if (isEmpty(str)) {
            return null;
        }
        char first = str.charAt(0);
        switch (first) {
            case 'm':
            case 'M':
                return Male;
            case 'f':
            case 'F':
                return Female;
            default:
                return null;
        }
    }
}
