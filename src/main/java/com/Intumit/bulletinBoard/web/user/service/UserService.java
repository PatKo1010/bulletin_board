package com.Intumit.bulletinBoard.web.user.service;

import com.Intumit.bulletinBoard.web.user.User;

public interface UserService {
	public User findByEmailAndPassword(String email, String password) throws Exception;

}
