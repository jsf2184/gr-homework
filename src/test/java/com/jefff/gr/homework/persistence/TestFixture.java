package com.jefff.gr.homework.persistence;

import com.jefff.gr.homework.model.Gender;
import com.jefff.gr.homework.persistence.PersonEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFixture
{
    public static final LocalDate MAY_1_1991 = LocalDate.of(1991, 5, 1);
    public static final LocalDate SEPTEMBER_1_1992 = LocalDate.of(1992, 9, 1);
    public static final LocalDate JULY_1_1993 = LocalDate.of(1993, 7, 1);
    public static final LocalDate AUGUST_1_1994 = LocalDate.of(1994, 8, 1);
    public static final LocalDate JUNE_1_1995 = LocalDate.of(1995, 6, 1);


    public static final PersonEntity GEORGE_JONES_1991 = new PersonEntity("Jones", "George", Gender.Male, "maize", MAY_1_1991);
    public static final PersonEntity ELLIE_JONES_1993 = new PersonEntity("Jones", "Ellie", Gender.Female, "red", JULY_1_1993);
    public static final PersonEntity BOB_SMITH_1992 = new PersonEntity("Smith", "Bob", Gender.Male, "pink", SEPTEMBER_1_1992);
    public static final PersonEntity JON_SMITH_1993 = new PersonEntity("Smith", "Jon", Gender.Male, "orange", JULY_1_1993);
    public static final PersonEntity JANE_SMITH_1994 = new PersonEntity("Smith", "Jane", Gender.Female, "black", AUGUST_1_1994);
    public static final PersonEntity SUE_SMITH_1995 = new PersonEntity("Smith", "Sue", Gender.Female, "blue", JUNE_1_1995);

    public static final List<PersonEntity> PEOPLE_LIST = Arrays.asList(GEORGE_JONES_1991,
                                                                       ELLIE_JONES_1993,
                                                                       BOB_SMITH_1992,
                                                                       JON_SMITH_1993,
                                                                       JANE_SMITH_1994,
                                                                       SUE_SMITH_1995);
}
