package com.sample.springboot.restapi.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.restapi.dao.DataStoreUserDAO;
import com.sample.springboot.restapi.entity.ResultJson;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private DataStoreUserDAO userDao;
	
	@RequestMapping(value = "/getusers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResultJson> freeVideos(HttpServletRequest request, HttpServletResponse response) {

		try {
			ResultJson data = new ResultJson();
			data.setUsers((userDao.getUsers("10")));
			return new ResponseEntity<ResultJson>(data, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson data = new ResultJson();
			data.setMessage("Sorry! something went wrong.");
			return new ResponseEntity<ResultJson>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
