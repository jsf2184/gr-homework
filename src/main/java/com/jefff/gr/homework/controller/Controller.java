package com.jefff.gr.homework.controller;

import com.jefff.gr.homework.model.Person;
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
    public List<Person> getRecords() {
        Person joe = new Person("Smith", "Zack", "Male", "05/29/1995", "Maize");
        Person mike = new Person("Smith", "Alex", "Male", "07/10/1993", "Blue");
        return Arrays.asList(joe, mike);
    }

    @PostMapping( value = "records")
    public Person createRecord(@RequestBody final String recordStr)
    {
        LOGGER.info(String.format("createRecord(): recordStr = %s", recordStr));
        return new Person("Smith", "Joe", "Male", "05/29/1995", "Maize");
    }
}
