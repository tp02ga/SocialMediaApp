package net.javavideotutorials.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.javavideotutorials.example.domain.User;

@Entity
@Table(name="pending_friendship")
public class PendingFriendship
{
	// bidirectional onetomany relationship
	
	private Long id;
	private User userId;
  private User friendId;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	@ManyToOne()
	public User getUserId()
	{
		return userId;
	}
	public void setUserId(User userId)
	{
		this.userId = userId;
	}
	
	@ManyToOne()
	public User getFriendId()
	{
		return friendId;
	}
	public void setFriendId(User friendId)
	{
		this.friendId = friendId;
	}
  
  
  
  
  
}
