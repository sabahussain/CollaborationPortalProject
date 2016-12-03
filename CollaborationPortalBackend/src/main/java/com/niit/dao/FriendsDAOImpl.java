package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.Friends;

@EnableTransactionManagement
@Repository
public class FriendsDAOImpl implements FriendsDAO {
	
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(FriendsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
		private Integer getMaxId()
		{
			String hql = "select max{id} from friend";
			Query query = getSession().createQuery(hql);
			Integer maxID = (Integer) query.uniqueResult();
			return maxID;
		}
		
		@Transactional
		public boolean save(Friends friends){	
			try{
			 getSession().save(friends);
		     return true;
			}catch (Exception e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(Friends friends){
			try{
			getSession().update(friends);
		    return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}

		@Transactional
		public Friends get(String userID, String friendID) {
			String hql = "from Friend where userID=" + "'" + userID + "' and friendID= '" + friendID;
			Query query = getSession().createQuery(hql);
			List<Friends> list = (List<Friends>) query.list();
			if(list != null && !list.isEmpty()) {
				return list.get(0);
			}
			return null;
			
		}

		@Transactional
		public boolean delete(String userID, String friendID) {
			Friends friend = new Friends();
			friend.setFriendID(friendID);
			friend.setUserID(userID);
			getSession().delete(friend);
			return true;
			
		}

		@Transactional
		public List<Friends> getMyFriend(String userID) {
			String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "A'";
			Query query =getSession().createQuery(hql);
			List<Friends> list = (List<Friends>) query.list();
			return list;
			
		}

		@Transactional
		public List<Friends> getNewFriendRequests(String userID) {
			String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "N'";
			Query query =getSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friends> list = (List<Friends>) query.list();
			return list;
		}

		
		@Transactional
		public void setOnline(String userID) {
			Logger.debug("Starting of the method setOnline");
			String hql = "UPDATE Friend SET isOnline = 'Y' where userID = '" + userID + "'";
			Logger.debug("hql: " + hql);
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOnline");
		}
		

		@Transactional
		public void setOffLine(String userID) {
			Logger.debug("Starting of the method setOffline");
			String hql = "UPDATE Friend SET isOnline = 'N' where userID = '" + userID + "'";
			Logger.debug("hql: " + hql);
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOffline");
		}

		
}
