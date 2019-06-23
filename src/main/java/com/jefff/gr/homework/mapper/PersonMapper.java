package com.jefff.gr.homework.mapper;

import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersonEntity;
import com.jefff.gr.homework.utility.DateUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapper {

    private static final Logger log = Logger.getLogger(PersonMapper.class);

    private final Splitter _splitter;

    public PersonMapper(Splitter splitter) {
        _splitter = splitter;
        log.info("PersonMapper constructor");
    }

    public Person toPerson(String personStr)
    {
        List<String> parts = _splitter.split(personStr);
        if (parts.size() != 5)
        {
            throw new UsageException(UsageError.NumFieldsError);
        }
        Person result = Person.of(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4));
        return result;
    }

    public Person toPerson(PersonEntity personEntity) {
        return Person.of(personEntity.getLastName(),
                         personEntity.getFirstName(),
                         personEntity.getGender().toString(),
                         personEntity.getFavoriteColor(),
                         DateUtility.toString(personEntity.getBirthDate()));
    }

    public PersonEntity toPersonEntity(Person person) {
        return  new PersonEntity(person.getLastName(),
                                 person.getFirstName(),
                                 person.getGender(),
                                 person.getFavoriteColor(),
                                 DateUtility.toDate(person.getBirthDate()));
    }

}
