package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;

@EnableTransactionManagement
@Repository
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
	
	@Transactional
	public boolean save(Blog blog){	
		try{
		  getSession().save(blog);
	      return true;
		}catch (Exception e ){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Transactional
	public boolean update(Blog blog){
		try{
			getSession().update(blog);
	        return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}
	@Transactional
	public boolean delete(Blog blog){
		try{
	       getSession().delete(blog);
	       return true;
		} catch (Exception e){
			//TODO Auto-generated catch block
	       e.printStackTrace();
	       return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Blog> list(){
		
    String hql = "from Blog";
	Query query =getSession().createQuery(hql);
	
	List<Blog> listBlog = query.list();
	if(listBlog == null  || listBlog.isEmpty())
	{
		 return null;
		 
	}
	return query.list();
	}

	@Transactional
	public Blog get(int id) {
        String hql="from Blog where id = " + "'" + id + "'";
		Query query=getSession().createQuery(hql);
		List<Blog> list=query.list();
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
	public Blog delete(int id) 
	{
		Blog BlogToDelete = new Blog();
		BlogToDelete.setId(id);
		getSession().delete(BlogToDelete);
		return null;
	}

	
}
