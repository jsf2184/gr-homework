package com.jefff.gr.homework.console.output;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ReportGenerator
{
    private static final Logger log = Logger.getLogger(ReportGenerator.class);

    PeopleService peopleService;
    PersonMapper personMapper;
    String filePrefix;
    private PrintWriterFactory printWriterFactory;

    public ReportGenerator(PeopleService peopleService,
                           PersonMapper personMapper,
                           String filePrefix,
                           PrintWriterFactory printWriterFactory)
    {
        this.peopleService = peopleService;
        this.personMapper = personMapper;
        this.filePrefix = filePrefix;
        this.printWriterFactory = printWriterFactory;
    }

    public void writeReport(PersonCompareType compareType)
    {
        final String fileName = getFileName(compareType);
        IPrintWriter printWriter;

        try
        {
            printWriter = printWriterFactory.createPrintWriter(fileName);
        } catch (IOException e)
        {
            log.error(String.format("Unable to create PrintWriter for file: %s", fileName));
            return;
        }

        List<Person> people = peopleService.sortBy(compareType);
        printWriter.println("[");
        boolean wrotePersonYet = false;
        for (Person person : people)
        {
            final String jsonPersonString = personMapper.toJsonString(person);
            if (wrotePersonYet)
            {
                printWriter.println(",");
            }
            printWriter.print("    " + jsonPersonString);
            wrotePersonYet = true;
        }
        if (wrotePersonYet)
        {
            printWriter.println("");
        }
        printWriter.println("]");
        printWriter.close();
    }

    public String getFileName(PersonCompareType compareType)
    {
        String res = filePrefix + "." + compareType.name() + ".json";
        return res;
    }
}
