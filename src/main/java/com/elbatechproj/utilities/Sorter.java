package com.elbatechproj.utilities;

import com.elbatechproj.domain.PeopleByCountry;
import java.util.Comparator;

public class Sorter implements Comparator<PeopleByCountry>
{
    public int compare(PeopleByCountry a, PeopleByCountry b)
    {
        return Double.compare(b.percentage, a.percentage);
    }
}