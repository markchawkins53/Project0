package com.revature.dao;

import com.revature.pojo.User;

public interface UserDatabaseDAO {
	
	//Serialization
	public void serializeDB ();
	public boolean deserializeDB(User.UserType userType);
	
	//Modify Database
	public boolean checkUserExists (String username);
	public boolean addUser (String username, String password);
	public boolean removeUser (String username);
	public void updateUser (User userToUpdate);
	public User getUser (String username);
}
