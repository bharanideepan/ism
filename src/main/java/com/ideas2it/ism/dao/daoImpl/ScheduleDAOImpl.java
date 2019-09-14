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
	public int totalCountForDate(String date) {
        int size = 0;        
        try {
        	System.out.println("repository "+ date);
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("select count(id) from Schedule where date_time like :date");
            query.setParameter("date", date+"%");
            if (null != query.uniqueResult()) {
                size = ((Number)query.uniqueResult()).intValue();
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } 
		return size;
	}

}
