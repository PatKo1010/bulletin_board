package com.Intumit.bulletinBoard.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Intumit.bulletinBoard.web.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public ResponseEntity<User> login (@RequestBody User user, HttpServletRequest request){
		User result;
		try {
			result = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("user", result);
			return new ResponseEntity<> (result, HttpStatus.OK);
		} catch (Exception e) {
			result = null;
			return ResponseEntity.notFound().build();
		}
	} 

}
