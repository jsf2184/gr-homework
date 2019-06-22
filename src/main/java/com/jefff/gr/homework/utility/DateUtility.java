package com.jefff.gr.homework.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y") ;

    public static LocalDate toDate(final String src)
    {
        if (src == null)
        {
            return null;
        }
        try {
            LocalDate res = LocalDate.parse(src, formatter);
            return res;
        } catch (DateTimeParseException ignore)
        {
            return null;
        }
    }

    public static String toString(LocalDate src)
    {
        if (src == null)
        {
            return null;
        }
        String res = src.format(formatter);
        return res;
    }


}
