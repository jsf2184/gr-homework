package com.jefff.gr.homework.service;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.mapper.Splitter;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.persistence.PersonEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jefff.gr.homework.persistence.TestFixture.*;
import static org.mockito.Mockito.*;

public class PeopleServiceTest
{
    final Splitter splitter = new Splitter();
    final PersonMapper personMapper = new PersonMapper(splitter);

    @Test
    public void testPeopleServiceProperlyDeleagates()
    {
        PersonMapper personMapper = mock(PersonMapper.class);
        PersistenceService persistenceService = mock(PersistenceService.class);
        PeopleService peopleService = new PeopleService(persistenceService, personMapper);
        String personStr = "personStr";
        Person person = mock(Person.class);
        when(personMapper.toPerson(personStr)).thenReturn(person);
        PersonEntity personEntity = mock(PersonEntity.class);
        when(personMapper.toPersonEntity(person)).thenReturn(personEntity);
        when(personMapper.toPerson(personEntity)).thenReturn(person);
        when(persistenceService.addPerson(personEntity)).thenReturn(personEntity);
        final Person result = peopleService.addPerson(personStr);
        Assert.assertSame(person, result);
        verify(persistenceService, times(1)).addPerson(personEntity);
    }
    @Test
    public void testSortByGenderThenLastAsc()
    {
        final List<PersonEntity> people = Arrays.asList(GEORGE_JONES_1991,
                                                        ELLIE_JONES_1993,
                                                        BOB_SMITH_1992,
                                                        JANE_SMITH_1994);

        PeopleService peopleService = create(people);
        final List<Person> actual = peopleService.sortBy(PersonCompareType.ByGenderThenLastAsc);


        final List<Person> expected = Arrays.asList(personMapper.toPerson(ELLIE_JONES_1993),
                                                    personMapper.toPerson(JANE_SMITH_1994),
                                                    personMapper.toPerson(GEORGE_JONES_1991),
                                                    personMapper.toPerson(BOB_SMITH_1992));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSortByBirthdateAsc()
    {
        final List<PersonEntity> people = Arrays.asList(GEORGE_JONES_1991,
                                                        ELLIE_JONES_1993,
                                                        BOB_SMITH_1992,
                                                        JANE_SMITH_1994);

        PeopleService peopleService = create(people);
        final List<Person> actual = peopleService.sortBy(PersonCompareType.ByBirthdateAsc);


        final List<Person> expected = Arrays.asList(personMapper.toPerson(GEORGE_JONES_1991),
                                                    personMapper.toPerson(BOB_SMITH_1992),
                                                    personMapper.toPerson(ELLIE_JONES_1993),
                                                    personMapper.toPerson(JANE_SMITH_1994));
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSortByLastNameDesc()
    {
        final List<PersonEntity> people = Arrays.asList(ELLIE_JONES_1993,BOB_SMITH_1992);

        PeopleService peopleService = create(people);
        final List<Person> actual = peopleService.sortBy(PersonCompareType.ByLastNameDesc);


        final List<Person> expected = Arrays.asList( personMapper.toPerson(BOB_SMITH_1992),
                                                     personMapper.toPerson(ELLIE_JONES_1993));
        Assert.assertEquals(expected, actual);

    }




    PeopleService create(List<PersonEntity> people  ) {
        PersistenceService persistenceService = new PersistenceService();
        PeopleService peopleService = new PeopleService(persistenceService, personMapper);
        people.forEach(peopleService::addPerson);
        return peopleService;
    }


}