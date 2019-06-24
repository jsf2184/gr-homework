package com.jefff.gr.homework.console;

import com.jefff.gr.homework.service.PeopleService;
import org.apache.log4j.Logger;

import java.util.stream.Stream;

public class LineHandler
{
    private static final Logger log = Logger.getLogger(LineHandler.class);

    Stream<String> stream;
    private PeopleService peopleService;
    private String filename;
    private int lineNum = 0;
    private int goodLines = 0;

    public LineHandler(Stream<String> stream, PeopleService peopleService, String filename)
    {
        this.stream = stream;
        this.peopleService = peopleService;
        this.filename = filename;
    }

    public void processStream()
    {
        stream.forEach(this::processLine);
        log.info(String.format("Finished processing file: %s, %d / %d  good lines", filename, goodLines, lineNum));

    }

    public void processLine(String line)
    {
        lineNum++;
        try
        {
            peopleService.addPerson(line);
            goodLines++;
        } catch (Exception e)
        {
            log.error(String.format("file: %s, line: %d: %s ", filename, lineNum, e.getMessage()));
        }
    }
}
