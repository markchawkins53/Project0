package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;
import com.revature.pojo.User;

public class UserDAOPostgres implements UserDAO {

	@Override
	public boolean add(User user) {
		String sql = "insert into users (cus_username, cus_password, a_user_type) values (?, ?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getUserType().ordinal());
			
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from users where cus_username = ?";
				
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet resultSet = stmt.executeQuery();
			User user = new User();
			
			while(resultSet.next()) {
				String name = resultSet.getString(1);
				String pass = resultSet.getString(2);
	//User.UserType userType = user.getUserTypeByInt(resultSet.getInt(3));
				
	//return new User(name, pass, userType);
				
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

}
