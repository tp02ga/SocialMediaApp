package net.javavideotutorials.example.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.javavideotutorials.example.dao.MessageDao;
import net.javavideotutorials.example.dao.PendingFriendshipDao;
import net.javavideotutorials.example.dao.UserDao;
import net.javavideotutorials.example.domain.Message;
import net.javavideotutorials.example.domain.PendingFriendship;
import net.javavideotutorials.example.domain.User;
import net.javavideotutorials.example.form.FriendshipForm;
import net.javavideotutorials.example.form.SearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class HomeController
{
  @Autowired
  MessageDao messageDao;
  @Autowired
  UserDao userDao;
  @Autowired
  PendingFriendshipDao pendingFriendshipDao;
  
  @RequestMapping(value="home", method=RequestMethod.GET)
  public String homeGet (ModelMap model, HttpServletRequest request)
  {
  	User user = (User) request.getSession().getAttribute("user");
  	
    populateHomeModel(model, user);
    return "app/home";
  }

	private void populateHomeModel(ModelMap model, User user)
  {
	  List<Message> messages = messageDao.getFriendsMessages(user);
    List<PendingFriendship> friendships = pendingFriendshipDao.getPendingFriendships(user);
    
    Message message = new Message();
    
    FriendshipForm friendshipForm = new FriendshipForm();
    
    model.put("friendshipForm", friendshipForm);
    model.put("friendships", friendships);
    model.put("message", message);
    model.put("messages", messages);
  }
  
  @RequestMapping(value="home", method=RequestMethod.POST)
  public String homePost (@ModelAttribute("message") Message message, 
  		@ModelAttribute("friendshipForm") FriendshipForm friendshipForm,
  		ModelMap model, HttpServletRequest request)
  {
  	User user = (User) request.getSession().getAttribute("user");
  	
  	if (friendshipForm.getPendingFriendshipUserId() != null && "acceptFriendRequest".equals(friendshipForm.getAction()))
  	{
  		User userById = userDao.getUserById(friendshipForm.getPendingFriendshipUserId());
  		userDao.addFriend(user, userById);
  		userDao.deleteFriendRequest(user, userById);
  	}
  	else if (friendshipForm.getPendingFriendshipUserId() != null && "declineFriendRequest".equals(friendshipForm.getAction()))
  	{
  		User userById = userDao.getUserById(friendshipForm.getPendingFriendshipUserId());
  		userDao.deleteFriendRequest(user, userById);
  	}
  	else if (message.getContent() != null)
  	{
  		message.setUser(user);
  		messageDao.save(message);
  	}
  	
  	populateHomeModel(model, user);
    
  	return "app/home";
  }
  
  @RequestMapping(value="home/{messageId}", method=RequestMethod.GET)
  public String homeGetByMessageId (@PathVariable("messageId") Long messageId, ModelMap model)
  {
    Message messageById = messageDao.getMessageById(messageId);
    List<Message> messages = new ArrayList<Message>();
    messages.add(messageById);
    model.put("messages", messages);
    return "app/home";
  }
  
  @RequestMapping(value="friend", method=RequestMethod.GET)
  public String friendGet (ModelMap model, HttpServletRequest request)
  {
    SearchForm searchForm = new SearchForm();
    
    model.put("searchForm", searchForm);
    return "app/friend";
  }
  
  @RequestMapping(value="friend", method=RequestMethod.POST)
  public String friendPost (@ModelAttribute("searchForm") SearchForm searchForm, ModelMap model, HttpServletRequest request)
  {
  	String action = searchForm.getAction();
  	if ("Yes".equals(action))
  	{
  		// add the friend to the current user!
  		User user = (User) request.getSession().getAttribute("user");
  		User friend = userDao.getUserByUsername(searchForm.getSearchString());
  		userDao.addFriendRequest(user, friend);
  	}
  	else
  	{
	  	User userByUsername = userDao.getUserByUsername(searchForm.getSearchString());
	  	model.put("searchResult", userByUsername);
  	}
  	return "app/friend";
  }
}
