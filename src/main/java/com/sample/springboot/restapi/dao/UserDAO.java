package com.sample.springboot.restapi.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.springboot.restapi.entity.User;

@Component
public interface UserDAO {
	List<User> getUsers(String startCursor) throws SQLException;
}
