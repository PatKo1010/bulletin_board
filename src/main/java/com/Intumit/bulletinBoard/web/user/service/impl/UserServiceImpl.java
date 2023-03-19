package com.Intumit.bulletinBoard.web.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Intumit.bulletinBoard.web.user.User;
import com.Intumit.bulletinBoard.web.user.UserRepository;
import com.Intumit.bulletinBoard.web.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User findByEmailAndPassword(String email, String password) throws Exception {
		return userRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("No Such User"));
	}
	
	
	

}
