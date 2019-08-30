package com.sample.springboot.restapi.dao;

import java.sql.SQLException;
import java.util.List;

import com.sample.springboot.restapi.entity.User;

public interface UserDAO {
	
	 List<User> getUsers(String startCursor) throws SQLException;
}
