package com.jaggu.spring.boot.auth.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class JagguUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pk_id")
	private long id;
	private String userName;
	private String email;
	private String password;
	private String phone;
	private String role;
}
