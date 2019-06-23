package com.jefff.gr.homework.persistence;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Gender;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersistenceService {

    private static final Logger log = Logger.getLogger(PersistenceService.class);

    List<PersonEntity> people;

    public PersistenceService() {
        log.info("PersistenceService constructor");
        people = new ArrayList<>();
    }

    public Stream<PersonEntity> getPeopleStream() {
        return people.stream();
    }

    public void addPerson(PersonEntity personEntity) {
        people.add(personEntity);
    }
}
