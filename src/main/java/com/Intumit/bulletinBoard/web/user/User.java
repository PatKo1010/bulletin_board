package com.Intumit.bulletinBoard.web.user;

import java.io.Serializable;
import java.util.List;

import com.Intumit.bulletinBoard.web.bullet.Bullet;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name ="user")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@NonNull
	@Column(name = "user_name")
	private String username;
	
	@NonNull
	@Column(name = "email")
	private String email;
	
	@NonNull
	@Column(name = "password")
	private String password;
	
	@NonNull
	@Column(name ="role")
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy ="user")
	private List<Bullet> bullets;

	@Override
	public String toString() {
		return "User [ID=" + ID + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}

}
