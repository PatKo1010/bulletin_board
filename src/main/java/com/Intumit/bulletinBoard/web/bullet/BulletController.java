package com.Intumit.bulletinBoard.web.bullet;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Intumit.bulletinBoard.web.bullet.service.BulletService;

@Controller
public class BulletController {
	
	@Autowired
	BulletService bulletService;
	
	@GetMapping("/bullets/{page}")
	@ResponseBody
	public HashMap<String, Object> findAll(@PathVariable("page") Integer page){
		Integer size = 3;
		return bulletService.findBulletsByPage(page, size);
	}
	

}
