package com.revature.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AuctionPosting implements Serializable{

	private Car car;
	private Map<String, Float> bids;
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Map<String, Float> getBids() {
		return bids;
	}
	public void setBids(Map<String, Float> bids) {
		this.bids = bids;
	}
	
}
