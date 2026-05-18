package com.ideas2it.ism.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.dao.ScheduleDAO;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.exception.IsmException;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

    public List<Schedule> fetchSchedulesByLimit(int startId) throws IsmException  {
        List<Schedule> schedules = new ArrayList<Schedule>();
        try  {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("FROM Schedule", 
            		Schedule.class);
            query.setFirstResult(startId);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            schedules = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new IsmException(Constant.ERROR_RETRIEVING_CANDIDATES, e);
        }
        return schedules;    
    }

	@Override
	public List<Schedule> getSchedulesByDate(int pageNo, String date) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        try  {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("FROM Schedule  WHERE date_time like :date", 
            		Schedule.class);
            query.setFirstResult(pageNo);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            query.setParameter("date", date+"%");
            schedules = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return schedules;   
	}
	
	@Override
	public List<Schedule> fetchManagerSchedulesByLimit(Technology technology, int pageNo) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        try  {
        	System.out.println("dao");
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("select s FROM Schedule s inner join "
            		+ "s.candidate c WHERE c.technology = :technology");
            query.setFirstResult(pageNo);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            query.setParameter("technology", technology);
            schedules = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return schedules;   
	}

	@Override
	public int totalCountForDate(String date) {
        int size = 0;        
        try {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery(
            		"select count(id) from Schedule where date_time like :date");
            query.setParameter("date", date+"%");
            if (null != query.uniqueResult()) {
                size = ((Number)query.uniqueResult()).intValue();
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } 
		return size;
	}
	
	@Override
	public int totalCountFoTechnology(Technology technology) {
        int size = 0;        
        try {
        	System.out.println("dao");
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("select count(s.id) "
            		+ "from Schedule s inner join s.candidate c WHERE c.technology = :technology");
            query.setParameter("technology", technology);
            if (null != query.uniqueResult()) {
                size = ((Number)query.uniqueResult()).intValue();
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } 
		return size;
	}

	@Override
	public List<Schedule> getMangerSchedulesByDate(Technology technology, int pageNo, String date) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        try  {
        	System.out.println("dao"+technology);
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("select s FROM Schedule s inner join "
            		+ "s.candidate c WHERE date_time like :date and c.technology = :technology");
            query.setFirstResult(pageNo);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            query.setParameter("date", date+"%");
            query.setParameter("technology", technology);
            schedules = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return schedules; 
	}
}
