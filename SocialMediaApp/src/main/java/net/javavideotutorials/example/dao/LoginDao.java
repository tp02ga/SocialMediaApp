package net.javavideotutorials.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.javavideotutorials.example.domain.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoginDao
{
	@PersistenceContext 
	EntityManager em;
	
	public void createUser (User user)
	{
		Session session = (Session) em.getDelegate();
		
		session.save(user);
		
	}
	
  public User getUser (User user)
  {
  	Session session = (Session) em.getDelegate();
  	
  	User userFromDb = (User) session.createCriteria(User.class)
  			.add(Restrictions.eq("username", user.getUsername()))
  			.add(Restrictions.eq("password", user.getPassword()))
  			.uniqueResult();
  	
  	return userFromDb;
  }
}
