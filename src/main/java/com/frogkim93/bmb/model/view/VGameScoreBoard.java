package com.frogkim93.bmb.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.frogkim93.bmb.constants.GameType;
import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "v_gamescoreboard")
@IdClass(VGameScoreBoardPK.class)
public class VGameScoreBoard {
	@Id
	@Column(nullable = false)
	private int seq;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date gameDate;
	
	@Column(nullable = false)
	private GameType gameType;
	
	@Column(nullable = false)
	private int teamIndex;
	
	@Id
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "memberSeq")
	private Members member;
	
	@Column(nullable = false)
	private String scoreList;
}
