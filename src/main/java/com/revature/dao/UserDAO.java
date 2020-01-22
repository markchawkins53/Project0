package com.revature.dao;

import com.revature.pojo.User;

public interface UserDAO {
	//Modify
		public boolean add(User user);
		
		//Access
		public User getByUsername(String username);
}
