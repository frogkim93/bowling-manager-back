package com.frogkim93.bmb.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "v_teamwithmaster")
public class VTeamWithMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "memberSeq")
	private Members member;
	
	@Column(nullable = false)
	private int teamIndex;
	
	@ManyToOne(targetEntity = Accounts.class)
	@JoinColumn(name = "masterSeq")
	private Accounts master;
}
