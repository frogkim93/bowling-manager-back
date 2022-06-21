package com.frogkim93.bmb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.frogkim93.bmb.constants.Gender;
import com.frogkim93.bmb.model.view.VMembers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "members")
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Accounts.class)
	@JoinColumn(name = "masterSeq")
	private Accounts master;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Gender gender;
	
	@OneToOne(targetEntity = VMembers.class)
	@JoinColumn(name = "seq")
	private VMembers vMember;
}
