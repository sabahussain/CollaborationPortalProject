package com.niit.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.dao.FriendsDAOImpl;
import com.niit.model.Friends;
import com.niit.model.User;

@RestController

public class FriendController {

	@Autowired
	FriendsDAOImpl friendsDAOImpl;
	
	@Autowired
	Friends friends;
	
	@RequestMapping(value="/myFriends",method = RequestMethod.GET)
    public ResponseEntity<List<Friends>> getMyFriends(HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<Friends> myFriends = friendsDAOImpl.getMyFriend(loggedInUser.getUsername());
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendID}", method = RequestMethod.POST)
	public ResponseEntity<Friends> sendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session) {
		
		User loggedInUser=(User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getUsername());
		friends.setFriendID(friendID);
		friends.setStatus("N");
		friends.setIsOnline('Y');
		friendsDAOImpl.save(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> unFriend(@PathVariable("friendID") String friendID, HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getUsername());
		friends.setFriendID(friendID);
		friends.setStatus("U");//N -> New , R-> Rejected , A -> Accepted, U->Unfriend
		friendsDAOImpl.save(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> rejectFriendFriendRequest(@PathVariable("friendID") String friendID, HttpSession session){
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getUsername());
		friends.setFriendID(friendID);
		friends.setStatus("R");
		friendsDAOImpl.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/getMyFriendRequests",method = RequestMethod.GET)
	public ResponseEntity<Friends> getMyFriendRequests(HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friendsDAOImpl.getNewFriendRequests(loggedInUser.getUsername());
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friends> acceptFriendRequest(@PathVariable("friendID") String friendID, HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friends.setUserID(loggedInUser.getUsername());
		friends.setFriendID(friendID);
		friends.setStatus("A");
		friendsDAOImpl.update(friends);
		return new ResponseEntity<Friends>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/myFriends/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Friends>> getMyFriendsTemp(@PathVariable("id") String id) {
	    List<Friends> myFriends = friendsDAOImpl.getMyFriend(id);
		return new ResponseEntity<List<Friends>>(myFriends, HttpStatus.OK);
	}
	
	
}
