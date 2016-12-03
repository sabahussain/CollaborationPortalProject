package com.niit.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.Forum;

@EnableTransactionManagement
@Repository
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
	
		@Transactional
		public boolean save(Forum forum){	
			try{
			  getSession().save(forum);
		return true;
			}catch (Exception e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(Forum forum){
			
			try{
				getSession().update(forum);
		        return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
		@Transactional
		public boolean delete(Forum forum){
			try{
		       getSession().delete(forum);
		       return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}
				
		@Transactional
		public List<Forum> list(){
		String hql = "from Forum";
        Query query =getSession().createQuery(hql);
		List<Forum> listForum = query.list();
		if(listForum == null  || listForum.isEmpty())
				{
					 return null;	 
				}
				return query.list();
	}
}
