package com.frogkim93.bmb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Entity(name = "teammappings")
public class TeamMappings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Teams.class)
	@JoinColumn(name = "teamSeq")
	private Teams team;
	
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "memberSeq")
	private Members member;
	
	@Column(nullable = false)
	private int teamIndex;
}
