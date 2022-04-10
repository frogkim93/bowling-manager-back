package com.frogkim93.bmb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "accounts")
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@Column(name = "userId", nullable = false)
	private String userId;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "nickname", nullable = false)
	private String nickname;
	
	@Column(name = "regTime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regTime;
	
	@Column(name = "lastLogin", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
}
