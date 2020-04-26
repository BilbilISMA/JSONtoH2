package com.elbatechproj.interfaces;

import com.elbatechproj.domain.People;
import com.elbatechproj.domain.PeopleByCountry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeopleRepository
{
    public void save(List<People> list);
    public Iterable<People> getAll();
    public Iterable<PeopleByCountry> getPeoplePercentageByCountry();
}

