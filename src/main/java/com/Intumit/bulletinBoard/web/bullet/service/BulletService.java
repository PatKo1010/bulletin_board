package com.Intumit.bulletinBoard.web.bullet.service;

import java.util.HashMap;

import com.Intumit.bulletinBoard.web.bullet.Bullet;

public interface BulletService {
	public Bullet findById (Integer id) throws Exception;
	public HashMap<String, Object> findBulletsByPage(Integer pageNo , Integer size);
	public Bullet save (Bullet bullet);
	public void deleteById (Integer id);

}
