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
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.dao.CandidateDAO;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.exception.IsmException;

@Repository
public class CandidateDAOImpl implements CandidateDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

    public List<Candidate> fetchCandidatesByLimit(int startId) throws IsmException  {
        List<Candidate> candidates = new ArrayList<Candidate>();
        try  {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("FROM Candidate", 
                    Candidate.class);
            query.setFirstResult(startId);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            candidates = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new IsmException(Constant.ERROR_RETRIEVING_CANDIDATES, e);
        }
        return candidates;    
    }

	@Override
	public List<Candidate> fetchCandidatesByStatus(int pageNo, Result candidateResult) throws IsmException {
        List<Candidate> candidates = new ArrayList<Candidate>();
        try  {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("FROM Candidate where status = :candidateResult", 
                    Candidate.class);
            query.setParameter("candidateResult", candidateResult);
            query.setFirstResult(pageNo);
            query.setMaxResults(Constant.RETRIEVE_LIMIT);
            candidates = query.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new IsmException(Constant.ERROR_RETRIEVING_CANDIDATES, e);
        }
        return candidates;  
	}

	@Override
	public int getTotalCount(Result status) throws IsmException {
        int size = 0;        
        try {
        	Session session = entityManager.unwrap(Session.class);
            Query query = session.createQuery("select count(id) from Candidate " 
                    +"where status = :status");
            query.setParameter("status", status);
            if (null != query.uniqueResult()) {
                size = ((Number)query.uniqueResult()).intValue();
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            throw new IsmException(Constant.ERROR_RETRIEVING_CANDIDATES, e);
        } 
		return size;
	}
}
