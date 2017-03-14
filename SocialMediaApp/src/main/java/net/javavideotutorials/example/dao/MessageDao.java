package net.javavideotutorials.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.javavideotutorials.example.domain.Message;
import net.javavideotutorials.example.domain.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageDao
{
	@PersistenceContext 
	EntityManager em;
	
	@SuppressWarnings("unchecked")
  public List<Message> getMessages()
  {
  	Session session = (Session) em.getDelegate();
  	
    return session.createCriteria(Message.class).list();
  }
	
	@SuppressWarnings("unchecked")
  public List<Message> getFriendsMessages(User user)
	{
		Session session = (Session) em.getDelegate();
		
		user = (User) session.createCriteria(User.class).add(Restrictions.idEq(user.getId())).uniqueResult();
		if (!user.getFriends().isEmpty())
		{
			return session.createCriteria(Message.class)
					.add(Restrictions.in("user", user.getFriends()))
					.list();
		}
		else
		{
			return null;
		}
	}

  @SuppressWarnings("unchecked")
  public Message getMessageById(Long messageId)
  {
  	Session session = (Session) em.getDelegate();
  	
  	List<Message> messages = session.createCriteria(Message.class).add(Restrictions.eq("id", messageId)).list();
  	
  	if (messages.size() == 0)
  	{
  		return null;
  	}
  	else
  	{
  		return messages.get(0);
  	}
  }

	public void save(Message message)
  {
		Session session = (Session) em.getDelegate();
		
		session.save(message);
	  session.flush();
  }
}
