package com.jefff.gr.homework.persistence;


import com.jefff.gr.homework.model.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class PersonEntity {
    private UUID id;
    private String lastName;
    private String firstName;
    private Gender gender;
    private String favoriteColor;
    private LocalDate birthDate;

    public PersonEntity(String lastName,
                         String firstName,
                         Gender gender,
                         String favoriteColor,
                         LocalDate birthDate) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }
}
