package com.revature.dao;

import java.util.List;
import java.util.Map;

import com.revature.pojo.AuctionPosting;
import com.revature.pojo.User;

public interface AuctionDatabaseDAO {
	
	public void serializeDB (List<AuctionPosting> aucPost);
	public List<AuctionPosting> deserializeDB();
	public void updatePost (List<AuctionPosting> aucPost, AuctionPosting postToUpdate);
}
