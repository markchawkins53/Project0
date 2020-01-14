package com.revature.dao;

import java.util.Map;

import com.revature.pojo.User;

public interface UserDatabaseDAO {
	
	public void serializeDB (Map<String, User> userDB, String filename);
	public Map<String, User> deserializeDB(String filename);
	public void updateUser (Map<String, User> userDB, User userToUpdate);
}
