package com.jefff.gr.homework.service;

import com.jefff.gr.homework.persistence.PersonEntity;

import java.util.Comparator;

public enum PersonCompareType
{
    ByGenderThenLastAsc(Comparator.comparing(PersonEntity::getGender).thenComparing(PersonEntity::getLastName)),
    ByBirthdateAsc(Comparator.comparing(PersonEntity::getBirthDate)),
    ByLastNameAsc(Comparator.comparing(PersonEntity::getLastName)),
    ByLastNameDesc(ByLastNameAsc.comparator.reversed());

    Comparator<PersonEntity> comparator;

    PersonCompareType(Comparator<PersonEntity> comparator)
    {
        this.comparator = comparator;
    }

    public Comparator<PersonEntity> getComparator()
    {
        return comparator;
    }
}
