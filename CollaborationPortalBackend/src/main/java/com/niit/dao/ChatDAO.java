package com.niit.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.niit.model.Chat;
public interface ChatDAO {
	
		public boolean save(Chat chat);
		public boolean update(Chat chat);
		public boolean delete(Chat chat);
		public List<Chat> list();
	
}
