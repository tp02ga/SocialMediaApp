package net.javavideotutorials.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.javavideotutorials.example.domain.PendingFriendship;
import net.javavideotutorials.example.domain.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao
{
	@PersistenceContext 
	EntityManager em;
	
	public User getUserByUsername(String username)
	{
		Session session = (Session) em.getDelegate();
		
		return (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		
	}
	
	public void addFriend(User user, User friend)
	{
		Session session = (Session) em.getDelegate();
		
		user = (User) session.createCriteria(User.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		user.getFriends().add(friend);
		friend.getFriends().add(user);
		
		session.saveOrUpdate(user);
	}

	public void addFriendRequest(User user, User friend)
  {
		Session session = (Session) em.getDelegate();
		
		user = (User) session.createCriteria(User.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		
		PendingFriendship friendship = (PendingFriendship) session.createCriteria(PendingFriendship.class).add(Restrictions.eq("friendId", friend)).add(Restrictions.eqOrIsNull("userId", user)).uniqueResult();
		
		if (friendship != null)
		{
			return;
		}
		
		PendingFriendship pendingFriendship = new PendingFriendship();
		pendingFriendship.setFriendId(friend);
		pendingFriendship.setUserId(user);
		user.getPendingFriendship().add(pendingFriendship);
		
		session.saveOrUpdate(pendingFriendship);
  }

	public User getUserById(Long pendingFriendshipUserId)
  {
	  Session session = (Session) em.getDelegate();
		
		return (User) session.createCriteria(User.class).add(Restrictions.eq("id", pendingFriendshipUserId)).uniqueResult();
  }

	public void deleteFriendRequest(User user, User userById)
  {
		Session session = (Session) em.getDelegate();
	  
		PendingFriendship pf = (PendingFriendship) session.createCriteria(PendingFriendship.class).add(Restrictions.eq("friendId", user)).add(Restrictions.eqOrIsNull("userId", userById)).uniqueResult();
		
		session.delete(pf);
  }
	
}
