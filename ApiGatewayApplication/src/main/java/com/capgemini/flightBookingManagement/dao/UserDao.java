package com.capgemini.flightBookingManagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.flightBookingManagement.entities.User;

@Repository
public class UserDao {

	public static final String HASH_KEY = "User";
	@Autowired
	private RedisTemplate template;

	public User save(User user){
	        template.opsForHash().put(HASH_KEY,user.getUserName(),user);
	        return user;
	    }

	public User findByUserName(String username){
        return (User) template.opsForHash().get(HASH_KEY,username);
    }
}
