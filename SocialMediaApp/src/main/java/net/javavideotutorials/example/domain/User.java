package net.javavideotutorials.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User
{
	private Long id;
  private String username;
  private String password;
  private String accountType;
  private Boolean termsOfService;
  private Set<Message> messages;
  
  //User  ---->  Message
  // 1 (one)      * (many)
  
  
  // many to many
  // table A  <---->  table A  
  // table A  <---->  table B
  
  private Set<User> friends = new HashSet<User>();
  private Set<PendingFriendship> pendingFriendship;
  
  @OneToMany(mappedBy="friendId")	
	public Set<PendingFriendship> getPendingFriendship()
	{
		return pendingFriendship;
	}
	public void setPendingFriendship(Set<PendingFriendship> pendingFriendship)
	{
		this.pendingFriendship = pendingFriendship;
	}
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
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
  @JoinTable(name="user_friendship", 
       joinColumns={@JoinColumn(name="id")}, 
       inverseJoinColumns={@JoinColumn(name="friend_id")})
	public Set<User> getFriends()
	{
		return friends;
	}
	public void setFriends(Set<User> friends)
	{
		this.friends = friends;
	}
	public String getAccountType()
  {
    return accountType;
  }
  public void setAccountType(String accountType)
  {
    this.accountType = accountType;
  }
  public String getUsername()
  {
    return username;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public Boolean getTermsOfService()
  {
    return termsOfService;
  }
  public void setTermsOfService(Boolean termsOfService)
  {
    this.termsOfService = termsOfService;
  }
  
  @OneToMany(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	public Set<Message> getMessages()
	{
		return messages;
	}
	public void setMessages(Set<Message> messages)
	{
		this.messages = messages;
	}
  
}
