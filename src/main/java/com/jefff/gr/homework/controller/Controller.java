package com.jefff.gr.homework.controller;

import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "records")
public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);

    private PeopleService peopleService;

    public Controller(PeopleService peopleService) {
        log.info("Controller constructor");
        this.peopleService = peopleService;
    }

    @GetMapping(value = "")
    public List<Person> getAll(@RequestParam String compareType) {

        PersonCompareType personCompareType = PersonCompareType.toEnum(compareType);
        if (personCompareType == null) {
            throw new UsageException(UsageError.BadCompareType);
        }
        log.info(String.format("getAll, comapareType = %s", personCompareType));
        final List<Person> people = peopleService.sortBy(personCompareType);
        return people;
    }

    @GetMapping(value = "{id}")
    public Person getPerson(@PathVariable UUID id) {
        Person person = peopleService.get(id);
        return person;
    }

    @PostMapping( )
    public Person createRecord(@RequestBody final String recordStr)
    {
        log.info(String.format("createRecord(): recordStr = %s", recordStr));
        final Person person = peopleService.addPerson(recordStr);
        return person;
    }
}
