package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.revature.pojo.User;

public class UserDatabaseSerialization implements UserDatabaseDAO{

	@Override
	public void serializeDB (Map<String, User> userDB, String userType) {
		String filename = userType + "DB.dat";
		
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(userDB);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, User> deserializeDB (String userType) {
		String filename = userType + "DB.dat";
		Map<String, User> userDB = null;
		
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			userDB = (HashMap<String, User>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			userDB = new HashMap<String, User>();
			return userDB;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return userDB;
	}
	
	@Override
	public void addUser () {
		
	}
	
	@Override
	public void removeUser () {
		
	}
	
	@Override
	public void updateUser (Map<String, User> userDB, User userToUpdate) {
		userDB.put(userToUpdate.getUsername(), userToUpdate);
		serializeDB(userDB, userToUpdate.getUserType().toString());
	}
	
	@Override
	public User getUser (String username, User.UserType userType) {
		Map<String, User> dbHolder = deserializeDB(userType.toString());
		
		try {
			dbHolder.get(username);
		} catch (ClassCastException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
				
		return dbHolder.get(username);
	}
}
