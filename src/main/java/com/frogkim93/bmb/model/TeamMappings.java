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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "teammappings")
public class TeamMappings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Games.class)
	@JoinColumn(name = "gameSeq")
	private Games game;
	
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "memberSeq")
	private Members member;
	
	@Column(nullable = false)
	private int teamIndex;
}
