package com.niit.dao;

import java.util.List;
import com.niit.model.Friends;
public interface FriendsDAO {

	public List<Friends> getMyFriend(String id);
	public Friends get(String userID, String friendID);
	public boolean save(Friends friends);
	public boolean update(Friends friends);
	public boolean delete(String userID, String friendID);
	public List<Friends> getNewFriendRequests(String userID);
	public void setOnline(String userID);
	public void setOffLine(String loggedInUserID);
	
}
