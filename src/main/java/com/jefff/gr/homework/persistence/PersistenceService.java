package com.jefff.gr.homework.persistence;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class PersistenceService {


    // Need a ConcurrentHashMap or something similar since users of our system could PUT or
    // GET simultaneously.
    //
    ConcurrentHashMap<UUID, PersonEntity> people;

    public PersistenceService() {
        people = new ConcurrentHashMap<>();
    }

    public Stream<PersonEntity> getPeopleStream() {
        return people.values().stream();
    }

    public PersonEntity addPerson(PersonEntity personEntity)
    {
        UUID id = UUID.randomUUID();
        personEntity.setId(id);
        people.put(id, personEntity);
        return personEntity;
    }
}
