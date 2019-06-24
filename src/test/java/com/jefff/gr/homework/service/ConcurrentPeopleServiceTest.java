package com.jefff.gr.homework.service;

import com.jefff.gr.homework.mapper.PersonMapper;
import com.jefff.gr.homework.mapper.Splitter;
import com.jefff.gr.homework.model.Person;
import com.jefff.gr.homework.persistence.PersistenceService;
import com.jefff.gr.homework.persistence.PersonEntity;
import com.jefff.gr.homework.persistence.PersonEntityGenerator;
import com.jefff.gr.homework.utility.LoggerUtility;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ConcurrentPeopleServiceTest
{
    private static final Logger log = Logger.getLogger(ConcurrentPeopleServiceTest.class);

    @BeforeClass
    public static void setup()
    {
        LoggerUtility.initRootLogger();
    }
    // Each writer creates and adds a new Person, with a birthday for every day in a decade.
    public static class Writer
    {
        private int decadeStart;
        final PeopleService peopleService;
        final CountDownLatch countDownLatch;
        final PersonEntityGenerator personGenerator;

        public Writer(int decadeStart, String favoriteColor, PeopleService peopleService, CountDownLatch countDownLatch)
        {
            this.decadeStart = decadeStart;
            this.peopleService = peopleService;
            this.countDownLatch = countDownLatch;
            personGenerator = new PersonEntityGenerator(decadeStart, favoriteColor);
        }

        public void run()
        {
            PersonEntity personEntity;
            int cnt = 0;
            while ((personEntity = personGenerator.next()) != null)
            {
                peopleService.addPerson(personEntity);
                cnt++;
            }
            log.info(String.format("Writer handling decade: %d has finished and wrote %d records", decadeStart, cnt));
            countDownLatch.countDown();
        }
    }

    public static class Client
    {
        private int clientNum;
        final PeopleService peopleService;
        final CountDownLatch countDownLatch;
        private PersonCompareType _compareType;

        public Client(int clientNum,
                      PeopleService peopleService,
                      CountDownLatch countDownLatch,
                      PersonCompareType compareType)
        {
            this.clientNum = clientNum;
            this.peopleService = peopleService;
            this.countDownLatch = countDownLatch;
            _compareType = compareType;
        }

        public void run()
        {
            List<Person> people = peopleService.sortBy(_compareType);
            log.info(String.format("Client %d using compareType:%s sorted %d records", clientNum, _compareType.name(), people.size()));
            countDownLatch.countDown();
        }
    }

    @Test
    public void testConcurrency() throws InterruptedException
    {

        PersistenceService persistenceService = new PersistenceService();
        PersonMapper personMapper = new PersonMapper(new Splitter());
        int numClients = 10;
        int numWriters = numClients;
        PeopleService peopleService = new PeopleService(persistenceService, personMapper);

        CountDownLatch countDownLatch = new CountDownLatch(numClients + numWriters);
        List<Client> clients = createClients(numClients, peopleService, countDownLatch);
        List<Writer> writers = createWriters(numWriters, peopleService, countDownLatch);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < numClients; i++)
        {
            Writer writer = writers.get(i);
            Client client = clients.get(i);
            executorService.submit(writer::run);
            executorService.submit(client::run);
        }
        log.info("Wait for countdownLatch");
        countDownLatch.await();
        log.info("Done Waiting for countdownLatch");
    }

    List<Client> createClients(int num, PeopleService peopleService, CountDownLatch countDownLatch)
    {
        PersonCompareType[] values = PersonCompareType.values();
        int numCompareTypes = values.length;
        List<Client> result = new ArrayList<>();
        IntStream.range(0, num).forEach(i ->
                                        {
                                            PersonCompareType compareType = values[i % numCompareTypes];
                                            Client client = new Client(i, peopleService, countDownLatch, compareType);
                                            result.add(client);
                                        });
        return result;

    }

    List<Writer> createWriters(int num, PeopleService peopleService, CountDownLatch countDownLatch)
    {
        List<Writer> result = new ArrayList<>();
        for (int decade = 1900, i = 0; i < num; decade += 10, i++)
        {
            Writer writer = new Writer(decade, "blue", peopleService, countDownLatch);
            result.add(writer);
        }
        return result;
    }


}
