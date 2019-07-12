package com.jefff.gr.homework.console;

import com.jefff.gr.homework.console.output.PrintWriterFactory;
import com.jefff.gr.homework.console.output.ReportGenerator;
import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.mapper.Splitter;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ConsoleApplication
{
    private static final Logger log = Logger.getLogger(ConsoleApplication.class);

    PeopleService peopleService;
    ReportGenerator reportGenerator;

    public ConsoleApplication(PeopleService peopleService, ReportGenerator reportGenerator)
    {
        this.peopleService = peopleService;
        this.reportGenerator = reportGenerator;
    }

    public void run(String[] args)
    {
        // Read and process all the input files present
        for (String fname : args)
        {
            try (Stream<String> inputStream = Files.lines(Paths.get(fname)))
            {
                LineHandler lineHandler = new LineHandler(inputStream, peopleService, fname);
                lineHandler.processStream();

            } catch (IOException e)
            {
                log.error(String.format("Unable to open file: %s", fname));
            }
        }
        // Generate report files in each of the 3 required sort orders
        reportGenerator.writeReport(PersonCompareType.ByGenderThenLastAsc);
        reportGenerator.writeReport(PersonCompareType.ByBirthdateAsc);
        reportGenerator.writeReport(PersonCompareType.ByLastNameDesc);
    }

    public static ConsoleApplication create()
    {
        PersistenceService persistenceService = new PersistenceService();
        PersonMapper personMapper = new PersonMapper(new Splitter());
        PeopleService peopleService = new PeopleService(persistenceService, personMapper);
        PrintWriterFactory printWriterFactory = new PrintWriterFactory();
        ReportGenerator reportGenerator = new ReportGenerator(peopleService,
                                                              personMapper,
                                                              "Report",
                                                              printWriterFactory);
        ConsoleApplication consoleApplication = new ConsoleApplication(peopleService,
                                                                       reportGenerator);
        return consoleApplication;

    }


}
