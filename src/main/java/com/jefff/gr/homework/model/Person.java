package com.jefff.gr.homework.model;


import com.jefff.gr.homework.exceptions.UsageError;
import com.jefff.gr.homework.exceptions.UsageException;
import com.jefff.gr.homework.utility.DateUtility;
import com.jefff.gr.homework.utility.StringUtility;

import java.time.LocalDate;

public class Person {
    private String lastName;
    private String firstName;
    private Gender gender;
    private String favoriteColor;
    private String birthDate;

    private Person(String lastName, String firstName, Gender gender, String favoriteColor, String birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.favoriteColor = favoriteColor;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public static Person of(String lastName,
                            String firstName,
                            String genderStr,
                            String favoriteColor,
                            String birthDateStr) {
        Gender gender = Gender.toEnum(genderStr);
        if (gender == null) {
            throw new UsageException(UsageError.GenderError);
        }
        LocalDate birthDate = DateUtility.toDate(birthDateStr);

        if (birthDate == null) {
            throw new UsageException(UsageError.BirthDateError);
        }

        Person person = new Person(StringUtility.capitalize(lastName),
                                   StringUtility.capitalize(firstName),
                                   gender,
                                   StringUtility.capitalize(favoriteColor),
                                   DateUtility.toString(birthDate));
        return person;
    }


}
