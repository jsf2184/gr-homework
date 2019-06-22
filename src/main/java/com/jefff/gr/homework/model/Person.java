package com.jefff.gr.homework.model;


public class Person {
    private String lastName;
    private String firstName;
    private Gender gender;
    private String birthDate;
    private String favoriteColor;

    private Person(String lastName, String firstName, Gender gender, String birthDate, String favoriteColor) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.favoriteColor = favoriteColor;
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

    public static Person of(String lastName, String firstName, String genderStr, String birthDate, String favoriteColor) {

    }

    public static String
}
