package com.Intumit.bulletinBoard.web.bullet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BulletRepository extends CrudRepository<Bullet, Integer> {
	
	Page<Bullet> findAll(Pageable pageable);

}
