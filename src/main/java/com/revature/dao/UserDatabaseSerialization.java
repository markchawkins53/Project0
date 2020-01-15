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
	
//========================================================================
//					Serialization
//========================================================================
	//Save Database to file
	@Override
	public void serializeDB () {
		//Save database to database based on User Type
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
	
	//Open a User Database and apply it to  a variable 
	//If database does not exist, make a new one
	@SuppressWarnings("unchecked")
	@Override
	public boolean deserializeDB (User.UserType userType) {
		//Open database to database based on User Type
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

//========================================================================
//						Modify Database
//========================================================================
	//Check to see if a user exists in the database
	@Override
	public boolean checkUserExists (String username) {		
		if (dbHolder.get(username) == null)
			return false;
		
		return true;
	}
	
	//Add a user to the database
	@Override
	public boolean addUser (String username, String password) {
		User newUser = new User();

		if (checkUserExists(username))
			return false;
		
		//Assign all user information and insert into database
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setOwnedCars(new LinkedList<Car>());
		newUser.setUserType(dbUserType);
		
		dbHolder.put(username, newUser);
		
		return true;
	}
	
	//Remove a user from the database
	@Override
	public boolean removeUser (String username) {
		if (!checkUserExists(username))
			return false;
		
		dbHolder.remove(username);
		
		return true;
	}
	
	//Update a user on the database with with information given
	@Override
	public void updateUser (User updatedUser) {		
		dbHolder.put(updatedUser.getUsername(), updatedUser);
	}
	
	//Get a User from the database
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
	
	//Return the User Database
	public static Map<String, User> getDbHolder() {
		return dbHolder;
	}
}
