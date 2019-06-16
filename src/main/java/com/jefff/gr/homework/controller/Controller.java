package com.jefff.gr.homework.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Slf4j
@RestController
@RequestMapping( value = "people")
public class Controller {

    @Data
    @AllArgsConstructor
    public static class Record implements Serializable {
        private String name;

    }
    public Controller() {
        LOGGER.info("Controller constructor");
    }

    @GetMapping( value = "records")
    public String getRecords() {
        Record joe = new Record("Joe");
        List<Record> records = Arrays.asList(joe, new Record("Mike"));
        String s = records.toString();
        return s;
    }

    @PostMapping( value = "records")
    public String createRecord(@RequestBody final String recordStr)
    {
        LOGGER.info(String.format("createRecord(): recordStr = %s", recordStr));
        return recordStr;
    }
}
