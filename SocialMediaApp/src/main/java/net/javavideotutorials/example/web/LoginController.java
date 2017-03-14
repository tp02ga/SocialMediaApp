package net.javavideotutorials.example.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.javavideotutorials.example.domain.User;
import net.javavideotutorials.example.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class LoginController implements ApplicationContextAware
{
  private ApplicationContext applicationContext;
  
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * Return a view (ie. login.jsp)
	 * @param locale
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login (Locale locale, Model model) {
		// GET vs. POST
	  //
	  // GET -> typically when a user loads a page/URL
	  // POST -> typically when a user is submitting information FROM a particular page/URL
    model.addAttribute("user", new User());
    
    loginService = applicationContext.getBean(LoginService.class);
    
    logger.info(loginService.toString());
    
		return "user/login";
	}

	
	/**
	 * Returns a view (ie. login.jsp)
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
  public String loginPost (@ModelAttribute("user") User user, Locale locale, Model model, HttpServletRequest request) {
    User userFromDatabase = loginService.isUsernameAndPasswordValid(user);
    
    if (userFromDatabase != null)
    {
    	request.getSession().setAttribute("user", userFromDatabase);
      model.addAttribute("user", userFromDatabase);
      return "redirect:/app/home.htm";
    }
    else
    {
      model.addAttribute("result", "Invalid Username and Password!");
      return "user/login";
    }
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)
  public String registerGet (Locale locale, Model model) 
	{
		User user = new User();
		populateModel(model);
		
		model.addAttribute("user", user);
		
	  return "user/register";
	}

	private void populateModel(Model model)
  {
    List<String> accountTypes = new ArrayList<String>();
	  
	  accountTypes.add("Bronze");
	  accountTypes.add("Silver");
	  accountTypes.add("Gold");
	  accountTypes.add("Platinum");
	  
	  model.addAttribute("mySpecialAccountTypes", accountTypes);
  }
	
	@RequestMapping(value="register", method=RequestMethod.POST)
  public String registerPost (@ModelAttribute("user") User user, Model model) 
	{
		loginService.createUser(user);
		
		populateModel(model);
    model.addAttribute("user", user);
	  return "user/register";
	}
	
  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException
  {
    this.applicationContext = applicationContext;
  }
}
