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
@Entity(name = "gamescores")
public class GameScores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Games.class)
	@JoinColumn(name = "gameSeq")
	private Games game;
	
	@ManyToOne(targetEntity = TeamMappings.class)
	@JoinColumn(name = "teamMappingSeq")
	private TeamMappings teamMapping;
	
	@Column(nullable = false)
	private int gameIndex;
	
	@Column(nullable = false)
	private int score;
}
