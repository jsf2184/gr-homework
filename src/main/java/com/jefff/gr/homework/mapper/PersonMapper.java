package com.jefff.gr.homework.mapper;

import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.model.Person;

import java.util.List;

public class PersonMapper {

    private final Splitter _splitter;

    public PersonMapper(Splitter splitter) {
        _splitter = splitter;
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

}
