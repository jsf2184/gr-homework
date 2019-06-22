package com.jefff.gr.homework.mapper;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateUtilityTest {

    @Test
    public void toDateTest() {
        LocalDate expected = LocalDate.of(2019, 3, 7);
        Assert.assertEquals(expected, DateUtility.toDate("3/7/2019"));
        Assert.assertEquals(expected, DateUtility.toDate("03/07/2019"));
    }

    @Test
    public void toDateWithEmptyInputTest() {
        Assert.assertNull(DateUtility.toDate(""));
        Assert.assertNull(DateUtility.toDate(null));
    }

    @Test
    public void toDateWithBadInputTest() {
        Assert.assertNull(DateUtility.toDate("xyz"));
        Assert.assertNull(DateUtility.toDate("3/2018"));
    }


    @Test
    public void toStringTest() {
        String actual = DateUtility.toString(LocalDate.of(2019, 3, 7));
        Assert.assertEquals("3/7/2019", actual);
    }

    @Test
    public void toStringWithNullInputTest() {
        String actual = DateUtility.toString(null);
        Assert.assertNull(actual);
    }

}