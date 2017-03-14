package net.javavideotutorials.example.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="message")
public class Message
{
  private Long id;
  private String content;
  private User user;
  
  //Message  ---->  User
  // * (many)      1 (one)
  
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
  public String getContent()
  {
    return content;
  }
  public void setContent(String content)
  {
    this.content = content;
  }

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="user_id")
  @NotNull
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
  
}
