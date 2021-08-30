package com.jefff.gr.homework.console;

import com.jefff.gr.homework.console.output.PrintWriterFactory;
import com.jefff.gr.homework.console.output.ReportGenerator;
import com.jefff.gr.homework.console.output.StrPrintWriter;
import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportGeneratorTest
{
    PeopleService peopleService;
    PersonMapper personMapper;
    PrintWriterFactory printWriterFactory;
    StrPrintWriter strPrintWriter;
    public static final String FILE_PREFIX = "Report";
    Person person1 = mock(Person.class);
    Person person2 = mock(Person.class);
    List<Person> people = Arrays.asList(person1, person2);
    PersonCompareType personCompareType = PersonCompareType.ByLastNameDesc;

    @Test
    public void testWriteReport()
    {
        final ReportGenerator reportGenerator = create();
        reportGenerator.writeReport(personCompareType);
        final String output = strPrintWriter.getOutput();
        String expected = "[\n" +
                "    Person1,\n" +
                "    Person2\n" +
                "]\n";
        Assert.assertEquals(expected, output);
    }

    @Test
    public void getFileName()
    {
        ReportGenerator reportGenerator = create();
        Assert.assertEquals("Report.ByGenderThenLastAsc.json", reportGenerator.getFileName(PersonCompareType.ByGenderThenLastAsc));
        Assert.assertEquals("Report.ByBirthdateAsc.json", reportGenerator.getFileName(PersonCompareType.ByBirthdateAsc));
        Assert.assertEquals("Report.ByLastNameDesc.json", reportGenerator.getFileName(PersonCompareType.ByLastNameDesc));
    }

    ReportGenerator create()
    {
        peopleService = mock(PeopleService.class);
        personMapper = mock(PersonMapper.class);
        printWriterFactory = mock(PrintWriterFactory.class);
        strPrintWriter = new StrPrintWriter();
        try
        {
            when(printWriterFactory.createPrintWriter("Report.ByLastNameDesc.json")).thenReturn(strPrintWriter);
        } catch (IOException ignore)
        {
        }
        when(peopleService.sortBy(personCompareType)).thenReturn(people);
        when(personMapper.toJsonString(person1)).thenReturn("Person1");
        when(personMapper.toJsonString(person2)).thenReturn("Person2");
        ReportGenerator result = new ReportGenerator(peopleService,
                                                     personMapper,
                                                     FILE_PREFIX,
                                                     printWriterFactory);
        return result;
    }
}