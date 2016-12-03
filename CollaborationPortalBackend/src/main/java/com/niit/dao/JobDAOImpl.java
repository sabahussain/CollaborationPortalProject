package com.niit.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.Job;
import com.niit.model.JobApplication;

@EnableTransactionManagement
@Repository
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
		
		@Transactional
		public boolean save(Job job){	
			
			try{
			 getSession().save(job);
			}catch(Exception e ){
				e.printStackTrace();
				return false;
			}
			return true;
		}	
		
		@Transactional
		public boolean save(JobApplication jobApplication){	
			
			try{
			  getSession().save(jobApplication);
			}catch (Exception e ){
				e.printStackTrace();
				return false;
			}
			return true;
		}	
		
		@Transactional
		public boolean postJob(Job job) {
			
			try {
				getSession().save(job);
			} catch (HibernateException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@Transactional
		public boolean update(Job job) {
			try {
				getSession().update(job);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@SuppressWarnings("unchecked")
		@Transactional
		public List<Job> list(){
		String hql = "from Job";
		Query query =getSession().createQuery(hql);
		List<Job> listJob = query.list();
		if(listJob == null  || listJob.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
		}

		
		@Transactional
		public List<Job> getAllVacantJobs() {
			String hql = "from Job where status = 'V' ";
			Query query = getSession().createQuery(hql);
			return query.list();
		}
		
		@Transactional
		public boolean applyForJob(JobApplication jobApplication) {
			try {
				getSession().save(jobApplication);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@Transactional
		public boolean updateJobApplication(JobApplication jobApplication) {
			try {
				getSession().update(jobApplication);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		@Transactional
		public JobApplication get(String userID, int jobID) {
			String hql = "from JobApplication where userID = '" + userID + "'and jobID = '"+ jobID+ "'";
			Query query = getSession().createQuery(hql);
			return (JobApplication) query.list();
		}

		@Transactional
		public JobApplication getMyAppliedJobs(String userID) {
			String hql = "from Job where id in (select id from JobApplication where userID = '" + userID + "')";
			Query query = getSession().createQuery(hql);
			return (JobApplication) query.list();
		}
		
		@Transactional
		public List<Job> getAllJobs() {
			String hql = "from Job";
			Query query = getSession().createQuery(hql);
			return query.list();
		}

		@Transactional
		public Job getJobDetails(int jobID) {
			return (Job)getSession().get(Job.class, jobID);
		}

		@Transactional
		public JobApplication getJobApplication(int jobID) {
			String hql= "from JobApplication where jobID = " + "'" + jobID + "'";
			Query query=getSession().createQuery(hql);
			List<JobApplication> list=query.list();
			if(list==null || list.isEmpty())
			{
				
				return null;
			}
			else
			{
				return list.get(0);
			}
	
		}

		public boolean delete(Job job) {
			try {
				getSession().delete(job);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

}
