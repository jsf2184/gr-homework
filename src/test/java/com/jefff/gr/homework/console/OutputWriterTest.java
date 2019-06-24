package com.jefff.gr.homework.console;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class OutputWriterTest
{

    PeopleService peopleService;
    PersonMapper personMapper;

    @Test
    public void getFileName()
    {
        OutputWriter outputWriter = create();
        Assert.assertEquals("Report.ByGenderThenLastAsc.json", outputWriter.getFileName(PersonCompareType.ByGenderThenLastAsc));
        Assert.assertEquals("Report.ByBirthdateAsc.json", outputWriter.getFileName(PersonCompareType.ByBirthdateAsc));
        Assert.assertEquals("Report.ByLastNameDesc.json", outputWriter.getFileName(PersonCompareType.ByLastNameDesc));
    }

    OutputWriter create()
    {
        peopleService = mock(PeopleService.class);
        personMapper = mock(PersonMapper.class);
        OutputWriter result = new OutputWriter(peopleService, personMapper, "Report");
        return result;
    }
}