package com.Intumit.bulletinBoard.web.bullet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BulletController {
	
	@GetMapping("/index")
	public String index () {
		System.out.print("into index controller");
		return "thymeleaf";
	}

}
