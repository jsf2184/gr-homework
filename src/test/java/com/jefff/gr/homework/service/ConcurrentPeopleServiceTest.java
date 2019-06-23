package com.jefff.gr.homework.service;

import com.jefff.gr.homework.persistence.PersonEntityGenerator;

import java.util.concurrent.CountDownLatch;

public class ConcurrentPeopleServiceTest
{
    // Each writer creates and adds a new Person, with a birthday for every day in a decade.
    public static class Writer {
        final int decadeStart;
        final PeopleService peopleService;
        final CountDownLatch countDownLatch;
        final PersonEntityGenerator personGenerator;

        public Writer(int decadeStart, PeopleService peopleService, CountDownLatch countDownLatch)
        {
            this.decadeStart = decadeStart;
            this.peopleService = peopleService;
            this.countDownLatch = countDownLatch;
            personGenerator = new PersonEntityGenerator(decadeStart, )
        }
    }
}
