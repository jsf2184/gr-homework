package com.jefff.gr.homework.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersonEntity;
import com.jefff.gr.homework.utility.DateUtility;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    private static final Logger log = Logger.getLogger(PersonMapper.class);
    private Gson gson = new GsonBuilder().create();
    private Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

    private final Splitter _splitter;

    public PersonMapper(Splitter splitter) {
        _splitter = splitter;
        gson = new GsonBuilder().create();
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

    public String toJsonString(Person person)
    {
        String result = gson.toJson(person);
        return result;
    }

    public List<String> toListOfJsonStrings(List<Person> people)
    {
        List<String> list = people.stream().map(this::toJsonString).collect(Collectors.toList());
        return list;
    }

    public String toJsonString(List<Person> people)
    {
        List<String> strings = toListOfJsonStrings(people);
        String result = prettyGson.toJson(strings);
        return result;
    }

}
