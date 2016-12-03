package com.niit.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.dao.FriendsDAOImpl;
import com.niit.dao.UserDAOImpl;
import com.niit.model.User;

@RestController
public class UserController {
    private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Autowired
	User user;
	
	@Autowired
	FriendsDAOImpl friendsDAOImpl;
	
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers()
	{
		Logger.debug("->->->calling method listAllUsers");
		List<User> user = userDAOImpl.list();
		
		if(user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
   @RequestMapping(value = "/user/" , method = RequestMethod.POST)
   public ResponseEntity<User> createuser(@RequestBody User user)
   {
	    Logger.debug("calling method createUser()");
	   
	   if(userDAOImpl.get(user.getUsername()) != null)
	   {//User Exist with this username
		   
	   user.setErrorCode("404");
	   user.setErrorMessage("User already exist with username : " +user.getUsername());
	   }
	   else
	   {
		   user.setStatus('N');
		   user.setIsOnline('N');
		   Logger.debug("saving user");
		    boolean flag = userDAOImpl.save(user);
		   
		   if(userDAOImpl.save(user)==false)
		   {
			   Logger.debug("Not able to register, Please contact admin");
			   user.setErrorCode("404");
			   user.setErrorMessage("Not able to register, please contact admin");
		   }
	   }
	   Logger.debug("Ending the method save user");
	   return new ResponseEntity<User>(user , HttpStatus.OK);
   }
	
   @RequestMapping(value = "/user/{id}" , method = RequestMethod.PUT)
   public ResponseEntity<User> updateuser(@PathVariable("username") String username, @RequestBody User user)
   {
	   Logger.debug("->->-> calling method UpdateUser");
	   if(userDAOImpl.get(username) == null)
	   { 
		   Logger.debug("->->->->User does not exist with username "+ user.getUsername());
		   user = new User();
		   user.setErrorMessage("User does not exist with username "+ user.getUsername());
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   
	   return new ResponseEntity<User>(user, HttpStatus.OK);
   }
   
   @RequestMapping(value = "/user/{username}" , method = RequestMethod.DELETE)
   public ResponseEntity<User> deleteuser (@PathVariable("username") String username, @RequestBody User user)
   {
	   Logger.debug("calling method deleteUser()");
	   if(userDAOImpl.get(username) == null)
	   { 
		   Logger.debug("User does not exist with username " +username);
		   user = new User();
		   user.setErrorMessage("User does not exist with username");
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   userDAOImpl.delete(user);
	   Logger.debug("->->->User Deleted Successfully");
	   return new ResponseEntity<User>(user, HttpStatus.OK); 
   }
   
   @RequestMapping(value="/user",method = RequestMethod.GET)
	public ResponseEntity<User> getuser(@PathVariable("username") String username)
	{
	   Logger.debug("calling method getUser()");
	   Logger.debug("username:"+username);
	   User user = userDAOImpl.get(username);
	   if(userDAOImpl.get(username) == null)
	   { 
		   Logger.debug("User does not exist with username" +username);
		   user = new User();
		   user.setErrorMessage("User does not exist with this username"+username);
		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	   }
	   Logger.debug("User exist with username " +username);
	   return new ResponseEntity<User>(user, HttpStatus.OK); 
	}
   
   @RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
   public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session)
   {
	   Logger.debug("Authenticating User method calling");
	   user = userDAOImpl.authenticate(user.getUsername(), user.getPassword());
	   if(user==null)
	   {
		   user = new User();
		   user.setErrorMessage("Invalid Credentials. Please Enter valid credentials");
	   }
	   else
	   {
		   Logger.debug("User exist with given credentials");
		   session.setAttribute("loggedInUser", user);
		   session.setAttribute("loggedInUserID", user.getUsername());
		   
	   }
	   return new ResponseEntity<User>(user, HttpStatus.OK); 
   }
   
   @RequestMapping(value="/user/logout" , method = RequestMethod.POST)
   public String logout(HttpSession session) {
	  Logger.debug("calling method logout()");
	  String loggedInUserID = (String) session.getAttribute("loggedInUserID");
	 
	  friendsDAOImpl.setOffLine(loggedInUserID);
	  userDAOImpl.setOffLine(loggedInUserID);
	  session.invalidate();
	   return ("You have successfully logged out");
   }
   
}
