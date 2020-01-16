package com.revature.dao;

import com.revature.pojo.AuctionPosting;
import com.revature.pojo.Car;

public interface AuctionDatabaseDAO {
	
	//Serialization
	public boolean serializeDB ();
	public boolean deserializeDB();
	
	//Modify Database
	public boolean checkPostExists (AuctionPosting post);
	public AuctionPosting createPost (Car carToAdd);
	public boolean addPost (AuctionPosting postToAdd);
	public boolean removePost (AuctionPosting aucPost);
	public void updatePost (AuctionPosting postToUpdate);
	public int getPostIndex (AuctionPosting post);
	
}
