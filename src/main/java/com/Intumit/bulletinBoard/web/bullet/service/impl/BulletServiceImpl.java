package com.Intumit.bulletinBoard.web.bullet.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Intumit.bulletinBoard.web.bullet.Bullet;
import com.Intumit.bulletinBoard.web.bullet.BulletRepository;
import com.Intumit.bulletinBoard.web.bullet.service.BulletService;

@Service
public class BulletServiceImpl implements BulletService {
	@Autowired
	BulletRepository  bulletRepo;

	@Override
	public Bullet findById(Integer id) throws Exception {
		return bulletRepo.findById(id).orElseThrow( () ->new Exception("No such bullet"));
	}

	@Override
	public HashMap<String, Object> findBulletsByPage(Integer pageNo, Integer size) {
		List<Bullet> bullets = new ArrayList<>();
		
		Pageable paging = PageRequest.of (pageNo, size);
		Page<Bullet> pageBullet = bulletRepo.findAll(paging);
		
		bullets = pageBullet.getContent();
		HashMap<String, Object> response = new HashMap<>();
		response.put ("bullets", bullets);
		response.put ("currentPage", pageBullet.getNumber());
		response.put("totalItems", pageBullet.getTotalElements());
	    response.put("totalPages", pageBullet.getTotalPages());
		return response;
	}

	@Override
	public Bullet save(Bullet bullet) {
		 return bulletRepo.save(bullet);
	}
	
	public String saveFile(MultipartFile file, String filePath) throws Exception{
		JSONObject object = new JSONObject();
		String fileName = file.getOriginalFilename();
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		file.transferTo(dest);
		return dest.getPath();
	}


	

}
