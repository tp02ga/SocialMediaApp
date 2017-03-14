package net.javavideotutorials.example.service;

import net.javavideotutorials.example.dao.LoginDao;
import net.javavideotutorials.example.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
  @Autowired
  private LoginDao loginDao;
  
  public User isUsernameAndPasswordValid (User user)
  {
    // what if, user is null
    /*user = null;*/
    
    // This will give a NullPointerException
    /*if (user.getUsername().equals(""))
    {
      
    }*/
    
    // Avoid a trip to the database since we know these credentials are not valid
    if ("".equals(user.getUsername()) || "".equals(user.getPassword()))
    {
      return null;
    }
    
    User userFromDatabase = loginDao.getUser(user);
    
    return userFromDatabase;
  }

	public void createUser(User user)
  {
	  loginDao.createUser(user);
  }
}
