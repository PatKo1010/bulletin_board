package com.Intumit.bulletinBoard.web.bullet;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Intumit.bulletinBoard.web.bullet.service.BulletService;
import com.Intumit.bulletinBoard.web.user.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BulletController {
	
	@Autowired
	BulletService bulletService;
	
	@GetMapping("/bullets/page/{page}")
	public String findAll(@PathVariable("page") Integer page, Model model, HttpServletRequest request){
		User user = (User) (request.getSession().getAttribute("user"));
		if (user == null) {
			return "login";
		}
		Integer size = 3;
		page = page-1;
		HashMap<String, Object> resultMap = bulletService.findBulletsByPage(page, size);
		model.addAttribute( "bullets", resultMap.get("bullets"));
		model.addAttribute( "currentPage", resultMap.get("currentPage"));
		model.addAttribute( "totalItems", resultMap.get("totalItems"));
		model.addAttribute( "totalPages", resultMap.get("totalPages"));
		return "bullets";
	}
	
	@GetMapping ("/bullet/add")
	public String addPage (Model model, HttpServletRequest request) {
		User user  = (User)request.getSession().getAttribute("user");
		if (user == null) {
			return "login";
		}
		model.addAttribute("action", "add");
		return "bulletin-add-edit";
	}
	
	@PostMapping ("bullet/add")
	public ResponseEntity<Bullet> add (@ModelAttribute Bullet bullet ){
		Bullet result = bulletService.add(bullet);
		return new ResponseEntity<>(result , HttpStatus.OK);
		
		
	}
	

}
