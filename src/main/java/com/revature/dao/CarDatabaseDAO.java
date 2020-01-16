package com.revature.dao;

import com.revature.pojo.Car;

public interface CarDatabaseDAO {

	public Car createCar (String make, String model, String year);

}
