package com.jefff.gr.homework.model;

import java.util.Date;

public class ErrorMessage
{
    final private Date timestamp;
    final private String message;


    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public String getMessage() {
        return message;
    }

}
