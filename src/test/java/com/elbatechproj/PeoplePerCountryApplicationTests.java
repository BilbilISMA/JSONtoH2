package com.elbatechproj;

import static org.junit.Assert.*;

import com.elbatechproj.domain.People;
import com.elbatechproj.domain.PeopleByCountry;
import com.elbatechproj.service.PeopleService;
import com.elbatechproj.utilities.Sorter;
import mockit.Tested;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class PeoplePerCountryApplicationTests
{

    @Test
    public void testSorter()
    {
        Sorter sorter = new Sorter();
        PeopleByCountry p1 = new PeopleByCountry("Kosovo",10);
        PeopleByCountry p2 = new PeopleByCountry("Albania",11);

        assertTrue(sorter.compare(p1,p2) < 0 );
    }

    @Tested
    private PeopleService peopleService;
    @Test
    public  void testSaveAndListPerson()
    {
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People("Bilbil","Isma","bilbil.isma@gmail.com","Kosovo", new Date(), false));
        peopleList.add(new People("Agron","Berisha","agron.berisha@gmail.com","Albania", new Date(), true));

        peopleService.save(peopleList);
        Iterable<People> returnedList = peopleService.list();
        long count = StreamSupport.stream(returnedList.spliterator(),false).count();
        assertTrue(count==2);
    }
}
