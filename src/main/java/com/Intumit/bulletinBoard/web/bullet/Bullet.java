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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name ="bullet")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Bullet {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@NonNull
	@Column(name = "title", nullable=false)
	private String title;
	
	@NonNull
	@Column(name = "content", nullable=false)
	private String content;
	
	@NonNull
	@Column(name = "release_date", nullable=false)
	private Date releaseDate;
	
	@NonNull
	@Column (name = "due_date", nullable=false)
	private Date dueDate;
	
	@NonNull
	@Column (name = "attached_file_name")
	private String attachedFile;
	
	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	
	

	@Override
	public String toString() {
		return "Bullet [ID=" + ID + ", title=" + title + ", content=" + content + ", releaseDate=" + releaseDate
				+ ", dueDate=" + dueDate + "]";
	}

}
