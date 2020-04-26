package com.elbatechproj;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elbatechproj.domain.People;
import com.elbatechproj.domain.PeopleByCountry;
import com.elbatechproj.service.PeopleService;


import com.elbatechproj.utilities.Sorter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class PeoplePerCountryApplication
{
    static Logger logger = Logger.getLogger(PeoplePerCountryApplication.class);
    public static void main(String[] args)
    {
        SpringApplication.run(PeoplePerCountryApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PeopleService peopleService)
    {
        return args ->
        {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<People>> typeReference = new TypeReference<List<People>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/people.json");
            PropertyConfigurator.configure("/resources/log4j.properties");
            try
            {
                System.out.println("Reading data from JSON file.");
                List<People> people = mapper.readValue(inputStream, typeReference);

                System.out.println("Saving the data to DB.");
                peopleService.save(people);
                System.out.println("Data saved successfully to DB.");

                System.out.println("Fetching all the rows from DB and mapping into list of 'People' class objects.");
                Iterable<People> peopleList = peopleService.list();

                System.out.println("Listing each object to console.");
                peopleList.forEach(System.out::println);

                Iterable<PeopleByCountry> peopleByCountries = peopleService.peopleByCountries();
                System.out.println("Getting percentage of people by country was successful.");

                List<PeopleByCountry> sortedList = new ArrayList<>();
                for (PeopleByCountry item:peopleByCountries)
                {
                    sortedList.add(item);
                }
                Collections.sort(sortedList, new Sorter());

                System.out.println("Writing the results on file.");
                sortedList.forEach(s-> logger.info(s.percentage +"% of people are from "+ s.country +", "));
                System.out.println("Results were successfully written on file.");

                System.in.read(); //Just keeping the app running
            }
            catch(IOException e)
            {
                System.out.println("Unable to save data to DB: "+ e.getMessage());
            }
        };
    }
}
