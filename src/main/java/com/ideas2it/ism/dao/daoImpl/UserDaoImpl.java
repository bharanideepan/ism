package com.ideas2it.ism.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.dao.CandidateDAO;
import com.ideas2it.ism.dao.UserDao;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public User findByName(String name) {
		User user = null;
		try {
        	Session session = entityManager.unwrap(Session.class);
        	Query query = session.createQuery("from user where name = :name");
        	query.setParameter("name", name);
        	//user = session.get(User.class, name);
            Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
            //user = (User)criteria.uniqueResult();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
}
