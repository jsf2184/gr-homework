package com.jefff.gr.homework.service;

import com.jefff.gr.homework.controller.Controller;
import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.persistence.PersonEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    private static final Logger log = Logger.getLogger(PeopleService.class);

    private PersistenceService persistenceService;
    private PersonMapper personMapper;

    public PeopleService(PersistenceService persistenceService, PersonMapper personMapper)
    {
        log.info("PeopleService constructor");
        this.persistenceService = persistenceService;
        this.personMapper = personMapper;
    }

    public Person addPerson(String personStr)
    {
        Person person = personMapper.toPerson(personStr);
        PersonEntity personEntity = personMapper.toPersonEntity(person);
        addPerson(personEntity);
        return person;
    }

    public void addPerson(PersonEntity personEntity) {
        persistenceService.addPerson(personEntity);
    }

    public List<Person> sortBy(PersonCompareType compareType)
    {
        final List<Person> result = persistenceService
                .getPeopleStream()
                .sorted(compareType.getComparator())
                .map(personEntity -> personMapper.toPerson(personEntity))
                .collect(Collectors.toList());
        return result;
    }
}
