package com.jefff.gr.homework.utility;

public class StringUtility {
    public static String capitalize(String input) {
        if (isEmpty(input)) {
            return input;
        }
        String first = input.substring(0, 1).toUpperCase();
        String end = input.length() > 1 ? input.substring(1).toLowerCase() : "";
        return first + end;

    }

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

}
