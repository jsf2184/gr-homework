package com.jefff.gr.homework.utility;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilityTest {

    @Test
    public void standardizeCase() {
        Assert.assertNull(StringUtility.capitalize(null));
        Assert.assertEquals("", StringUtility.capitalize(""));
        Assert.assertEquals("Abc", StringUtility.capitalize("ABC"));
        Assert.assertEquals("Abc", StringUtility.capitalize("abc"));
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(StringUtility.isEmpty(null));
        Assert.assertTrue(StringUtility.isEmpty(""));
        Assert.assertFalse(StringUtility.isEmpty("ABC"));
    }
}