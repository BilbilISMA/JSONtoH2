package com.elbatechproj.service;

import com.elbatechproj.domain.People;
import com.elbatechproj.domain.PeopleByCountry;
import com.elbatechproj.interfaces.IPeopleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeopleService
{
    private IPeopleRepository peopleRepository;

    public PeopleService(IPeopleRepository iPeopleRepository)
    {
        this.peopleRepository = iPeopleRepository;
    }

    public void save(List<People> people)
    {
        peopleRepository.save(people);
    }

    public Iterable<People> list() { return peopleRepository.getAll(); }

    public Iterable<PeopleByCountry> peopleByCountries() { return peopleRepository.getPeoplePercentageByCountry(); }
}
