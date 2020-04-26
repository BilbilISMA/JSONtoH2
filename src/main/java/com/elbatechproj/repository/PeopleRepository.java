package com.elbatechproj.repository;

import com.elbatechproj.domain.People;
import com.elbatechproj.domain.PeopleByCountry;
import com.elbatechproj.interfaces.IPeopleRepository;
import com.elbatechproj.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleRepository implements IPeopleRepository
{
    public PeopleRepository(){}

    public void save(List<People> people)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            for (People person:people)
            {
                session.save(person);
            }
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Iterable<People> getAll()
    {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            List<People> people = session.createQuery("from People", People.class).list();
            session.close();
            return  people;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return  null;
        }
    }

    public Iterable<PeopleByCountry> getPeoplePercentageByCountry()
    {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            long rowsCount = (long)session.createQuery("SELECT COUNT(*) FROM People").uniqueResult();
             Query q = session.createQuery("select new com.elbatechproj.domain.PeopleByCountry(p.country, (count(p.id) * 100 / :allCount) )  from People p  group by p.country", PeopleByCountry.class);
             q.setParameter("allCount", rowsCount);
             List<PeopleByCountry> peopleByCountry = q.list();
             session.close();
            return  peopleByCountry;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return  null;
        }
    }

}
