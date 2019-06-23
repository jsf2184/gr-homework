package com.jefff.gr.homework.controller;

import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping( value = "records")
public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);

    PeopleService peopleService;

    public Controller(PeopleService peopleService)
    {
        log.info("Controller constructor");
        this.peopleService = peopleService;
    }

    @GetMapping( value = "gender")
    public List<Person> getRecordsByGenderThenLastAsc() {
        final List<Person> people = peopleService.sortBy(PersonCompareType.ByGenderThenLastAsc);
        return people;
    }

    @GetMapping( value = "birthdate")
    public List<Person> getRecordsByBirthdateAsc() {
        final List<Person> people = peopleService.sortBy(PersonCompareType.ByBirthdateAsc);
        return people;
    }

    @GetMapping( value = "name")
    public List<Person> getRecordsByLastNameDesc() {
        final List<Person> people = peopleService.sortBy(PersonCompareType.ByLastNameDesc);
        return people;
    }


    @PostMapping( )
    public Person createRecord(@RequestBody final String recordStr)
    {
        log.info(String.format("createRecord(): recordStr = %s", recordStr));
        final Person person = peopleService.addPerson(recordStr);
        return person;
    }
}
