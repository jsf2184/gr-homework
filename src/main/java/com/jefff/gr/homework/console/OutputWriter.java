package com.jefff.gr.homework.console;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;

import java.util.List;

public class OutputWriter
{
    PeopleService peopleService;
    PersonMapper personMapper;
    String filePrefix;

    public OutputWriter(PeopleService peopleService, PersonMapper personMapper, String filePrefix)
    {
        this.peopleService = peopleService;
        this.personMapper = personMapper;
        this.filePrefix = filePrefix;
    }

    public void writeReport(PersonCompareType compareType)
    {
        List<Person> people = peopleService.sortBy(compareType);

    }

    public String getFileName(PersonCompareType compareType)
    {
        String res = filePrefix + "." + compareType.name() + ".json";
        return res;
    }
}
