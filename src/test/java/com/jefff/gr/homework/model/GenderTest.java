package com.jefff.gr.homework.model;

import org.junit.Assert;
import org.junit.Test;

public class GenderTest {

    @Test
    public void toEnum() {
        validate(null, null);
        validate("", null);
        validate("xyz", null);
        validate("malfeasance", Gender.Male);
        validate("m", Gender.Male);
        validate("M", Gender.Male);
        validate("Ma", Gender.Male);
        validate("ma", Gender.Male);
        validate("falfeasance", Gender.Female);
        validate("f", Gender.Female);
        validate("F", Gender.Female);
        validate("Fa", Gender.Female);
        validate("fa", Gender.Female);
    }

    public static void validate(String input, Gender expected) {
        Assert.assertEquals(expected, Gender.toEnum(input));
    }
}