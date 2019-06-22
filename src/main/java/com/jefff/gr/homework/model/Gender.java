package com.jefff.gr.homework.model;

public enum Gender {
    Male,
    Female;

    public static Gender toEnum(String str) {
        if (str == null) {
            return null;
        }

    }
}
