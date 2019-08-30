package com.sample.springboot.restapi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.sample.springboot.restapi.entity.User;


@Component
public class DataStoreUserDAO implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	 @Value("#{environment['PROJECT_ID']}")
	 private String PROJECT_ID;
	 
	 private Datastore datastore;
	 private KeyFactory keyFactory;
	
	 public DataStoreUserDAO() {
	   logger.info("DataStoreUserDAO :: env PROJECT_ID=[{}]",System.getenv("PROJECT_ID"));
	   if (PROJECT_ID == null) {
		   PROJECT_ID = System.getenv("PROJECT_ID");
	   } 
	   logger.info("DataStoreUserDAO :: PROJECT_ID=[{}]", PROJECT_ID);
	   datastore = DatastoreOptions.newBuilder()
               .setProjectId(PROJECT_ID)
               .build()
               .getService();
	   keyFactory = datastore.newKeyFactory().setKind("Users");
	 }
	 
	@Override
	public List<User> getUsers(String startCursorString) throws SQLException {
		/*
		 * List<User> users = new ArrayList<>(); User user = new User();
		 * user.setFirstname("Spring boot with data store"); users.add(user); return
		 * users;
		 */
		// TODO Auto-generated method stub
		Cursor startCursor = null;
		if (startCursorString != null && !startCursorString.equals("")) {
		  startCursor = Cursor.fromUrlSafe(startCursorString);
		}
		Query<Entity> query = Query.newEntityQueryBuilder()
	        .setKind("Users")
	        .setLimit(10)
	        .setStartCursor(startCursor)
	        .setOrderBy(OrderBy.asc("firstname"))
	        .build();
	    QueryResults<Entity> resultList = datastore.run(query);
	    List<User> users = prepareUsers(resultList);
		return users;
	}
	
	 public List<User> prepareUsers(QueryResults<Entity> resultList) {
	    List<User> users = new ArrayList<>();
	    while (resultList.hasNext()) {
	    	users.add(prepareUser(resultList.next()));
	    }
	    return users;
	  }
	 
	 public User prepareUser(Entity entity) {
		User user = new User();
		user.setFirstname(entity.getString("firstname"));
	    return user;
	  }
}