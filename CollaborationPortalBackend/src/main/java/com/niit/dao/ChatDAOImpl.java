package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.Chat;

@EnableTransactionManagement
@Repository
public class ChatDAOImpl implements ChatDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
	
  @Transactional
  public boolean save(Chat chat){	
			try{
			  getSession().save(chat);
		return true;
			}catch (Exception e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(Chat chat){
			try{
				getSession().update(chat);
		       return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		@Transactional
		public boolean delete(Chat chat){
			try{
		       getSession().delete(chat);
		       return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		
		
		@Transactional
		public List<Chat> list(){
		String hql = "from Chat";
		Query query =getSession().createQuery(hql);
		List<Chat> listChat = query.list();
		if(listChat == null  || listChat.isEmpty())
		{
			 return null;	 
		}
		return query.list();
		}
}
