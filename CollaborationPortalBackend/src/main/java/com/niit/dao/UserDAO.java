package com.niit.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.niit.model.User;

public interface UserDAO {
		public boolean save(User user);
		public boolean update(User user);
		public boolean delete(User user);
		public List<User> list();
		public User get(String id);
		public User authenticate(String username, String password);
		public void setOffLine(String username);
        public void setOnline(String username);	
	
}
