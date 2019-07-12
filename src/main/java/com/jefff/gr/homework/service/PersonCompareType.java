package com.jefff.gr.homework.service;

import com.jefff.gr.homework.persistence.PersonEntity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

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

    static Map<String, PersonCompareType> nameMap;
    public static final String CHOICES;


    static {
        nameMap = Arrays.stream(PersonCompareType.values())
                .collect(Collectors.toMap(pct -> pct.name().toLowerCase(), pct -> pct));
        StringBuilder sb = new StringBuilder();
        for (PersonCompareType personCompareType : PersonCompareType.values()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(personCompareType.name());
        }
        CHOICES = sb.toString();

    }


    public static PersonCompareType toEnum(String compareTypeStr) {
        return nameMap.get(compareTypeStr.toLowerCase());
    }
}
