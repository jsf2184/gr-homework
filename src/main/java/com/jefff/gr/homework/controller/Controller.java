package com.jefff.gr.homework.controller;

import com.jefff.gr.homework.model.Person;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping( value = "people")
public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);

    public static class Record implements Serializable {
        private String name;

    }
    public Controller() {
        log.info("Controller constructor");
    }

    @GetMapping( value = "records")
    public List<Person> getRecords() {
        Person joe = Person.of("Smith", "Zack", "Male", "05/29/1995", "Maize");
        Person mike = Person.of("Smith", "Alex", "Male", "07/10/1993", "Blue");
        return Arrays.asList(joe, mike);
    }

    @PostMapping( value = "records")
    public Person createRecord(@RequestBody final String recordStr)
    {
        log.info(String.format("createRecord(): recordStr = %s", recordStr));
        return Person.of("Smith", "Joe", "Male", "05/29/1995", "Maize");
    }
}
