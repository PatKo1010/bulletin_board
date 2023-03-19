package com.Intumit.bulletinBoard.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Intumit.bulletinBoard.web.bullet.Bullet;
import com.Intumit.bulletinBoard.web.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/logInPage")
	public String directToLogInPage () {
		return "login";
	}
	
	@PostMapping("/logIn")
	@ResponseBody
	public User login (@RequestBody User user){
		User result;
		try {
			result = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
//			return new ResponseEntity<>(result, HttpStatus.OK);
			return result;
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return null;
		}
	} 

}
