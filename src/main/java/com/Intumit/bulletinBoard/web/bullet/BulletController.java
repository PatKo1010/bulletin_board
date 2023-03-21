package com.Intumit.bulletinBoard.web.bullet;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Intumit.bulletinBoard.web.bullet.service.BulletService;
import com.Intumit.bulletinBoard.web.user.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BulletController {

	@Value("${file.upload.url}")
	private  String uploadFilePath;

	@Autowired
	BulletService bulletService;

	@GetMapping("/bullets/page/{page}")
	public String findAll(@PathVariable("page") Integer page, Model model, HttpServletRequest request) {
		User user = (User) (request.getSession().getAttribute("user"));
		if (user == null) {
			return "login";
		}
		Integer size = 3;
		page = page - 1;
		HashMap<String, Object> resultMap = bulletService.findBulletsByPage(page, size);
		model.addAttribute("bullets", resultMap.get("bullets"));
		model.addAttribute("currentPage", resultMap.get("currentPage"));
		model.addAttribute("totalItems", resultMap.get("totalItems"));
		model.addAttribute("totalPages", resultMap.get("totalPages"));
		return "bullets";
	}

	@GetMapping("/bullet/add")
	public String toAddPage(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "login";
		}
		model.addAttribute("action", "add");
		return "bulletin-add-edit";
	}
	
	@GetMapping ("/bullet/modify/{id}")
	public String toModifyPage (Model model, HttpServletRequest request, @PathVariable("id") String stId) throws NumberFormatException, Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "login";
		}
		
		Bullet bullet = bulletService.findById(Integer.valueOf(stId));
		
		model.addAttribute ("bullet", bullet);
		model.addAttribute("action", "modify");
		return "bulletin-add-edit";
		
	}

	@PostMapping("/bullet/submit")
	public ResponseEntity<Bullet> submit(@RequestParam("ID") String ID, @RequestParam("title") String title,
			@RequestParam("releaseDate") String stReleaseDate, @RequestParam("dueDate") String stDueDate,
			@RequestParam("content") String content, @RequestParam("attachedFile") MultipartFile file, HttpServletRequest request) throws Exception {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		String filePath = null;
		if (!file.isEmpty()) {
			try {
				filePath = saveFile(file, uploadFilePath );
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().build();
			}
		}
		Date releaseDate = formatDate(stReleaseDate);
		Date dueDate = formatDate(stDueDate);

		if (!"".equals(ID)) {
			Integer intId = Integer.valueOf(ID);
			try {
				Bullet tempBullet = bulletService.findById(intId);
				tempBullet.setTitle(title);
				tempBullet.setReleaseDate(releaseDate);
				tempBullet.setDueDate(dueDate);
				tempBullet.setContent(content);
				tempBullet.setAttachedFile(filePath);
				
				Bullet resultBullet = bulletService.save(tempBullet);
				return new ResponseEntity<> (resultBullet, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
			}

		} else {
			Bullet newBullet = new Bullet(title, content, releaseDate, dueDate, filePath, user);
			Bullet resultBullet = bulletService.save(newBullet);
			return new ResponseEntity<> (resultBullet, HttpStatus.OK);
		}
	}
	
	public static String saveFile(MultipartFile file, String filePath) throws Exception{
		String fileName = file.getOriginalFilename();
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		file.transferTo(dest);
		return dest.getPath();
	}

	public static Date formatDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd"); 
		return dateFormat.parse(dateString);
	}

}
