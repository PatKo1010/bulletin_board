package com.Intumit.bulletinBoard.web.user;

import java.util.List;

import com.Intumit.bulletinBoard.web.bullet.Bullet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name ="role")
	private String role;
	
	@OneToMany(mappedBy ="user")
	private List<Bullet> bullets;

}
