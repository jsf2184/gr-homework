package com.jefff.gr.homework.console;

import com.jefff.gr.homework.service.PeopleService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class LineHandlerTest
{

    PeopleService peopleService;
    List<String> strings;

    @Test
    public void processStream()
    {
        LineHandler lineHandler = create();
        lineHandler.processStream();
        verify(peopleService, times(1)).addPerson("a");
        verify(peopleService, times(1)).addPerson("b");
        verify(peopleService, times(1)).addPerson("c");
    }


    LineHandler create()
    {
        strings = Arrays.asList("a", "b", "c");
        peopleService = mock(PeopleService.class);

        LineHandler lineHandler = new LineHandler(strings.stream(), peopleService, "xyz.txt");
        return lineHandler;
    }
}