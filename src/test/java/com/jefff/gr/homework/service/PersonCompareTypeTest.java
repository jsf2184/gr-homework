package com.jefff.gr.homework.service;

import com.jefff.gr.homework.persistence.PersonEntity;
import com.jefff.gr.homework.persistence.TestFixture;
import org.junit.Test;

import java.util.function.Consumer;

import static com.jefff.gr.homework.service.PersonCompareType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonCompareTypeTest
{

    public static final Consumer<Integer> ASSERT_EQUALS = (i) -> assertEquals(0, (int) i);
    public static final Consumer<Integer> ASSERT_GREATER = (i) -> assertTrue(i > 0);
    public static final Consumer<Integer> ASSERT_LESS = (i) -> assertTrue(i < 0);

    @Test
    public void testByGenderThenLastAsc()
    {
        // females before males
        runTest(TestFixture.SUE_SMITH_1995, TestFixture.JON_SMITH_1993, ByGenderThenLastAsc, ASSERT_LESS);
        runTest(TestFixture.JON_SMITH_1993, TestFixture.SUE_SMITH_1995, ByGenderThenLastAsc, ASSERT_GREATER);

        // same gender, same lastname
        runTest(TestFixture.SUE_SMITH_1995, TestFixture.JANE_SMITH_1994, ByGenderThenLastAsc, ASSERT_EQUALS);
        runTest(TestFixture.JON_SMITH_1993, TestFixture.BOB_SMITH_1992, ByGenderThenLastAsc, ASSERT_EQUALS);

        // same gender, different lastname
        runTest(TestFixture.ELLIE_JONES_1993, TestFixture.JANE_SMITH_1994, ByGenderThenLastAsc, ASSERT_LESS);
        runTest(TestFixture.JANE_SMITH_1994, TestFixture.ELLIE_JONES_1993, ByGenderThenLastAsc, ASSERT_GREATER);
    }

    @Test
    public void testByBirthdateAsc()
    {
        // birthdate ascending
        runTest(TestFixture.JON_SMITH_1993, TestFixture.SUE_SMITH_1995, ByBirthdateAsc, ASSERT_LESS);
        runTest(TestFixture.SUE_SMITH_1995, TestFixture.JON_SMITH_1993, ByBirthdateAsc, ASSERT_GREATER);
        runTest(TestFixture.ELLIE_JONES_1993, TestFixture.JON_SMITH_1993, ByBirthdateAsc, ASSERT_EQUALS);
    }

    @Test
    public void testByLastnameDesc()
    {
        // lastname descending - so less than and greater than are flipped.
        runTest(TestFixture.ELLIE_JONES_1993, TestFixture.JON_SMITH_1993, ByLastNameDesc, ASSERT_GREATER);
        runTest(TestFixture.JON_SMITH_1993, TestFixture.ELLIE_JONES_1993, ByLastNameDesc, ASSERT_LESS);
        runTest(TestFixture.SUE_SMITH_1995, TestFixture.JON_SMITH_1993, ByLastNameDesc, ASSERT_EQUALS);
    }


    public void runTest(PersonEntity p1,
                        PersonEntity p2,
                        PersonCompareType comparator,
                        Consumer<Integer> assertion)
    {
        int actual = comparator.getComparator().compare(p1, p2);
        assertion.accept(actual);

    }
}