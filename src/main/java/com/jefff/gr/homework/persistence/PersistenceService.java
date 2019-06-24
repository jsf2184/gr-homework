package com.jefff.gr.homework.persistence;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class PersistenceService {

    private static final Logger log = Logger.getLogger(PersistenceService.class);

    // Need a ConcurrentHashMap or something similar since users of our system could PUT or
    // GET simultaneously.
    //
    ConcurrentHashMap<UUID, PersonEntity> people;

    public PersistenceService() {
        log.info("PersistenceService constructor");
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
