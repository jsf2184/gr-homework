package com.jefff.gr.homework.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ErrorMessageTest
{
    @Test
    public void testGetters()
    {
        Date now = new Date();
        String message = "message";
        ErrorMessage errorMessage = new ErrorMessage(now, message);
        Assert.assertEquals(now, errorMessage.getTimestamp());
        Assert.assertEquals(message, errorMessage.getMessage());
    }

}