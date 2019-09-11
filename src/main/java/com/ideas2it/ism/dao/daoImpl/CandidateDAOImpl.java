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
import com.ideas2it.ism.dao.CandidateDAO;
import com.ideas2it.ism.entity.Candidate;

@Repository
public class CandidateDAOImpl implements CandidateDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

    public List<Candidate> fetchCandidatesByLimit(int startId) {
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
        }
        return candidates;    
    }
}
