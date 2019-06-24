package com.jefff.gr.homework.console;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.mapper.Splitter;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ConsoleApplication
{
    private static final Logger log = Logger.getLogger(ConsoleApplication.class);

    PeopleService peopleService;
    PersonMapper personMapper = new PersonMapper(new Splitter());

    public ConsoleApplication()
    {
        this.peopleService = new PeopleService(new PersistenceService(), new PersonMapper(new Splitter()));
    }

    public void run(String[] args)
    {
        for (String fname : args)
        {
            //read file into stream, try-with-resources
            try (Stream<String> stream = Files.lines(Paths.get(fname)))
            {

                LineHandler lineHandler = new LineHandler(stream, peopleService, fname);
                lineHandler.processStream();
//                 stream.forEach(System.out::println);

            } catch (IOException e)
            {
                log.error(String.format("Unable to open file: %s", fname));
            }
        }
        List<Person> people = peopleService.sortBy(PersonCompareType.ByLastNameDesc);


    }


}
