package com.niit.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.User;

@EnableTransactionManagement
@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger Logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
		
		@Transactional
		public boolean save(User user){	
			try{
			    getSession().save(user);
		        return true;
			}catch (HibernateException e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(User user){
			try{
				getSession().update(user);
		        return true;
			} catch (HibernateException e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		@Transactional
		public boolean delete(User user){
			try{
		        getSession().delete(user);
		        return true;
			} catch (HibernateException e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		
		@SuppressWarnings("unchecked")
		@Transactional
		public List<User> list()
		{
		String hql = "from User";
		Query query =getSession().createQuery(hql);
		List<User> listUser = query.list();
		if(listUser == null  || listUser.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
		}
	
		@Transactional
		public User get(String username) 
		{
		String hql="from User where username = " + "'" + username + "'";
			Query query=getSession().createQuery(hql);
			@SuppressWarnings({ "unchecked" })
			List<User> list=query.list();
			if(list==null || list.isEmpty())
			{
	
				return null;
			}
			else
			{
				return list.get(0);
			}
		}

		@Transactional
		public User authenticate(String username, String password) 
		{
			String hql="from User where username = " + "'" + username + "'and " + " password='" + password+"'";
			Query query=getSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			if(list!=null  && !list.isEmpty())
			{
				return list.get(0);
			}
			return null;
		}
		
		@Transactional
		public void setOffLine(String username) {
			Logger.debug("Starting of the method setOnline");
			String hql = "UPDATE User SET isOnline = 'N' where username='" + username + "'";
			Logger.debug("hql: " + hql);
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOnline");
		}
		

		@Transactional
		public void setOnline(String username) {
			Logger.debug("Starting of the method setOffline");
			String hql = "UPDATE User SET isOnline = 'Y' where userID = '" + username + "'";
			Logger.debug("hql: " + hql);
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOffline");
	
		}

		
}
