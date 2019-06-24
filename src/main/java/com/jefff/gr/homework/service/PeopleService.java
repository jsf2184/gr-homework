package com.jefff.gr.homework.service;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.persistence.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    private PersistenceService persistenceService;
    private PersonMapper personMapper;

    public PeopleService(PersistenceService persistenceService, PersonMapper personMapper)
    {
        this.persistenceService = persistenceService;
        this.personMapper = personMapper;
    }

    public Person addPerson(String personStr)
    {
        Person person = personMapper.toPerson(personStr);
        PersonEntity personEntity = personMapper.toPersonEntity(person);
        personEntity = addPerson(personEntity);
        person = personMapper.toPerson(personEntity);
        return person;
    }

    public PersonEntity addPerson(PersonEntity personEntity)
    {
        personEntity = persistenceService.addPerson(personEntity);
        return personEntity;
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
