package com.jefff.gr.homework.persistence;

import com.jefff.gr.homework.model.Gender;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class PersonEntityGenerator
{
    private static final Logger log = Logger.getLogger(PersonEntityGenerator.class);

    final int startDecade;
    private String favColor;
    LocalDate lastBirthDate;

    public PersonEntityGenerator(int decadeStart, String favColor)
    {
        startDecade = decadeStart / 10;
        this.favColor = favColor;
        lastBirthDate = LocalDate.of(startDecade * 10, 1, 1); // Jan 1 of this decade
    }

    public static String genName() {
        return RandomStringUtils.random(8, true, false);
    }

    public LocalDate nextBirthDate() {
//        log.info(lastBirthDate);
        if (lastBirthDate.getYear() / 10 != startDecade)
        {
            // we are done.
            return  null;
        }
        LocalDate res = lastBirthDate;
        lastBirthDate = lastBirthDate.plusDays(1);
        return res;
    }

    public PersonEntity next() {
        LocalDate birthDate = nextBirthDate();
        if (birthDate == null) {
            return null;
        }
        Gender gender;
        if (birthDate.toEpochDay() %2 == 0) {
            gender = Gender.Female;
        } else {
            gender = Gender.Male;
        }

        final String name = genName();
        PersonEntity result = new PersonEntity(name, name, gender, favColor, birthDate);
        return result;
    }
}
