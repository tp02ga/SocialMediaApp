package net.javavideotutorials.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.javavideotutorials.example.domain.PendingFriendship;
import net.javavideotutorials.example.domain.User;

@Repository
@Transactional
public class PendingFriendshipDao
{
	@PersistenceContext 
	EntityManager em;
	
	@SuppressWarnings("unchecked")
  public List<PendingFriendship> getPendingFriendships(User user)
  {
		Session session = (Session) em.getDelegate();
	  
		return (List<PendingFriendship>) session.createCriteria(PendingFriendship.class).add(Restrictions.eq("friendId", user)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
  }

}
