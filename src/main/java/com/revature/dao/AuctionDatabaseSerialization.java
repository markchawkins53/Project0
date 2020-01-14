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
import java.util.Map;

import com.revature.pojo.AuctionPosting;
import com.revature.pojo.User;

public class AuctionDatabaseSerialization implements AuctionDatabaseDAO{

	@Override
	public void serializeDB (List<AuctionPosting> aucPost) {
		String filename = "LotAucDB.dat";
		
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(aucPost);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<AuctionPosting> deserializeDB() {
		String filename = "LotAucDB.dat";
		List<AuctionPosting> aucPosts = null;
		
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			aucPosts = (List<AuctionPosting>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			aucPosts = new LinkedList<AuctionPosting>();
			return aucPosts;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return aucPosts;
	}

	@Override
	public void updatePost(List<AuctionPosting> aucPost, AuctionPosting postToUpdate) {
		int postIndex = aucPost.indexOf(postToUpdate);		
		aucPost.set(postIndex, postToUpdate);
		serializeDB(aucPost);
	}

}
