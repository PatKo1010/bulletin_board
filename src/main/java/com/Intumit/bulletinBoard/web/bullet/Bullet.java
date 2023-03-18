package com.Intumit.bulletinBoard.web.bullet;

import java.util.Date;

import com.Intumit.bulletinBoard.web.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="bullet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bullet {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name = "title", nullable=false)
	private String title;
	
	@Column(name = "content", nullable=false)
	private String content;
	
	@Column(name = "release_date", nullable=false)
	private Date releaseDate;
	
	@Column (name = "due_date", nullable=false)
	private Date dueDate;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id", nullable=false)
	private User user;
	
	

}
