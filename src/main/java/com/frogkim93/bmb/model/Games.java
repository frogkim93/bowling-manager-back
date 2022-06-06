package com.frogkim93.bmb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.frogkim93.bmb.constants.GameType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "games")
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@ManyToOne(targetEntity = Teams.class)
	@JoinColumn(name = "teamSeq")
	private Teams team;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date gameDate;
	
	@Column(nullable = false)
	private GameType gameType;
}
