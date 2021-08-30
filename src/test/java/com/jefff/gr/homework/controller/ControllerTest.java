package com.jefff.gr.homework.controller;

import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.service.PeopleService;
import com.jefff.gr.homework.service.PersonCompareType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ControllerTest
{
    private List<Person> personList;
    private PeopleService peopleService;

    @Test
    public void getRecordsByGenderThenLastAsc()
    {
        Controller controller = create();
        when(peopleService.sortBy(PersonCompareType.ByGenderThenLastAsc)).thenReturn(personList);
        List<Person> result = controller.getAll(PersonCompareType.ByGenderThenLastAsc.name());
        Assert.assertSame(personList, result);
        verify(peopleService, times(1)).sortBy(PersonCompareType.ByGenderThenLastAsc);
    }

    @Test
    public void getRecordsByBirthdateAsc()
    {
        Controller controller = create();
        when(peopleService.sortBy(PersonCompareType.ByBirthdateAsc)).thenReturn(personList);
        List<Person> result = controller.getAll(PersonCompareType.ByBirthdateAsc.name());
        Assert.assertSame(personList, result);
        verify(peopleService, times(1)).sortBy(PersonCompareType.ByBirthdateAsc);

    }

    @Test
    public void getRecordsByLastNameDesc()
    {
        Controller controller = create();
        when(peopleService.sortBy(PersonCompareType.ByLastNameDesc)).thenReturn(personList);
        List<Person> result = controller.getAll(PersonCompareType.ByLastNameDesc.name());
        Assert.assertSame(personList, result);
        verify(peopleService, times(1)).sortBy(PersonCompareType.ByLastNameDesc);

    }

    @Test
    public void createRecord()
    {
        Controller controller = create();
        String message = "message";
        Person person = mock(Person.class);
        when(peopleService.addPerson(message)).thenReturn(person);
        Person actual = controller.createRecord(message);
        Assert.assertSame(person, actual);
        verify(peopleService, times(1)).addPerson(message);
    }

    private Controller create()
    {
        peopleService = mock(PeopleService.class);
        personList = new ArrayList<>();
        Controller controller = new Controller(peopleService);
        return controller;
    }
}