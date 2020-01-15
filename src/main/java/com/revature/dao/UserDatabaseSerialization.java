package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.revature.pojo.Car;
import com.revature.pojo.User;

public class UserDatabaseSerialization implements UserDatabaseDAO{

	private static Map<String, User> dbHolder;
	private static User.UserType dbUserType;
	
	@Override
	public void serializeDB () {
		String filename = dbUserType.toString() + "DB.dat";
		
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(dbHolder);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean deserializeDB (User.UserType userType) {
		String filename = userType.toString() + "DB.dat";
		dbUserType = userType;
		
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			dbHolder = (HashMap<String, User>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			dbHolder = new HashMap<String, User>();
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean checkUserExists (String username) {		
		if (dbHolder.get(username) == null)
			return false;
		
		return true;
	}
	
	@Override
	public boolean addUser (String username, String password) {
		User newUser = new User();

		if (checkUserExists(username))
			return false;
		
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setOwnedCars(new LinkedList<Car>());
		newUser.setUserType(dbUserType);
		
		dbHolder.put(username, newUser);
		
		return true;
	}
	
	@Override
	public boolean removeUser (String username) {
		if (!checkUserExists(username))
			return false;
		
		dbHolder.remove(username);
		
		return true;
	}
	
	@Override
	public void updateUser (User userToUpdate) {		
		dbHolder.put(userToUpdate.getUsername(), userToUpdate);
		serializeDB();
	}
	
	@Override
	public User getUser (String username) {
		
		try {
			dbHolder.get(username);
		} catch (ClassCastException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
		
		return dbHolder.get(username);
	}
	
	public static Map<String, User> getDbHolder() {
		return dbHolder;
	}
}
