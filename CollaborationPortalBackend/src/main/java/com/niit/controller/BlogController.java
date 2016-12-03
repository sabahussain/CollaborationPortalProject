package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.dao.BlogDAOImpl;
import com.niit.model.Blog;

@RestController
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BlogController {

	@Autowired
	private BlogDAOImpl blogDAOImpl;
	
	@Autowired
	private Blog blog;
	
	@RequestMapping(value = "/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getblogs()
	{
		List<Blog> blogs = blogDAOImpl.list();
		if(blogs == null)
		{
			blog = new Blog();
			blog.setErrorCode("404");
       	    blog.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blog{id}" , method = RequestMethod.GET)
	public Blog getBlog(@PathVariable("id")int id){
		
		Blog blog = blogDAOImpl.get(id);
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found with the id"+ id);
		}
		
		return blog;
	}
	
	@RequestMapping(value = "/blog/" , method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession httpsession) {
		
   		String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
		blog.setUserID(loggedInuserID);
		blog.setStatus("N");
		blogDAOImpl.save(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value="/blog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable int id) {
		if(blogDAOImpl.get(id)!=null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		blogDAOImpl.delete(id);
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	
}
