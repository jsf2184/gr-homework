package com.jefff.gr.homework.mapper;

import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.model.Gender;
import com.jefff.gr.homework.model.Person;
import org.junit.Assert;
import org.junit.Test;
public class PersonMapperTest {

    @Test
    public void testParsingErrors() {
        validateError(null, UsageError.NumFieldsError);
        validateError("", UsageError.NumFieldsError);
        validateError("a,b,c,d", UsageError.NumFieldsError);
        validateError("Smith Joe Boy blue 10/06/1930", UsageError.GenderError);
        validateError("Smith Joe m blue 106/1930", UsageError.BirthDateError);
    }

    @Test
    public void testGoodParse() {
        PersonMapper sut = new PersonMapper(new Splitter());
        Person person = sut.toPerson("sMITH joE maletype blUE 10/6/1930");
        Assert.assertEquals("Smith", person.getLastName());
        Assert.assertEquals("Joe", person.getFirstName());
        Assert.assertEquals(Gender.Male, person.getGender());
        Assert.assertEquals("Blue", person.getFavoriteColor());
        Assert.assertEquals("10/6/1930", person.getBirthDate());
    }

    public static void validateError(String input, UsageError expectedError) {
        PersonMapper sut = new PersonMapper(new Splitter());
        try {
            sut.toPerson(input);
        } catch (UsageException e) {
            Assert.assertEquals(expectedError, e.getUsageError());
            return;
        }
        Assert.fail();
    }

}