package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;

public class AuctionDatabaseSerialization implements AuctionDatabaseDAO{
	
	private static List<AuctionPosting> aucDB;

//========================================================================
//							Serialization
//========================================================================
	//Save Database to file
	@Override
	public boolean serializeDB () {
		String filename = "LotAucDB.dat";
		
		//Save to database
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(aucDB);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	//Open a User Database and apply it to  a variable 
	//If database does not exist, make a new one
	@SuppressWarnings("unchecked")
	@Override
	public boolean deserializeDB() {
		String filename = "LotAucDB.dat";
		
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			aucDB = (List<AuctionPosting>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			aucDB = new LinkedList<AuctionPosting>();
			return true;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

//========================================================================
//					Modify Database
//========================================================================
	
	//Check to see if a user exists in the database
	//@Override
	public boolean checkPostExists (AuctionPosting post) {		
		try {
			aucDB.contains(post);
		} catch (ClassCastException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		
		if (aucDB.contains(post))
			return true;
		
		return false;
	}
	
	//Create a new post
	@Override
	public AuctionPosting createPost (Car carToAdd) {
		AuctionPosting newPost = new AuctionPosting();
		
		newPost.setCar(carToAdd);
		newPost.setBids(new HashMap<String, Float>());
		
		return newPost;
	}
	
	
	//Add a post to the database
	@Override
	public boolean addPost (AuctionPosting postToAdd) {
		if (checkPostExists(postToAdd))
			return false;
		
		aucDB.add(postToAdd);
		
		return true;
	}
	
	//Remove a user from the database	
	@Override
	public boolean removePost (AuctionPosting aucPost) {
		if (!checkPostExists(aucPost))
			return false;
		
		aucDB.remove(aucPost);
		
		return true;
	}
	
	//Update a user on the database with with information given
	@Override
	public void updatePost (AuctionPosting postToUpdate) {
		int postIndex = aucDB.indexOf(postToUpdate);		
		aucDB.set(postIndex, postToUpdate);
	}
	
	@Override
	public int getPostIndex (AuctionPosting post) {
		if (!checkPostExists(post))
			return -1;
		
		return aucDB.indexOf(post);
	}
	
	//Get a User from the database
	//@Override
	public AuctionPosting getPost (int postIndex) {		
		try{
			aucDB.get(postIndex);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		
		return aucDB.get(postIndex);
	}
	
	public boolean isEmpty () {
		if (aucDB.isEmpty())
			return true;
		
		return false;
	}
	
	public int size () {
		return aucDB.size();
	}

}
