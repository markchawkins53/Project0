package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.Car;

public class CarDatabaseSerialization implements CarDatabaseDAO {

	@Override
	public Car createCar (String make, String model, String year) {
		Car newCar = new Car ();
		
		newCar.setMake(make);
		newCar.setModel(model);
		newCar.setYear(year);
		
		newCar.setRemPayments(new ArrayList<Float>());
		
		return newCar;
	}

}
