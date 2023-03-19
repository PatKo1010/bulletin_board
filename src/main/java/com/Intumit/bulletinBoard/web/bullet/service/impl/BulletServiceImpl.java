package com.Intumit.bulletinBoard.web.bullet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public Bullet edit(Bullet bullet) {
		 return bulletRepo.save(bullet);
	}

	@Override
	public Bullet add(Bullet bullet) {
		return bulletRepo.save(bullet);
	}

}
