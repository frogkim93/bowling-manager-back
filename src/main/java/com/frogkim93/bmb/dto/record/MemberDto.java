package com.frogkim93.bmb.dto.record;

import java.util.List;

import com.frogkim93.bmb.model.GameScores;
import com.frogkim93.bmb.model.Games;
import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDto {
	private int seq;
	private int teamMappingSeq;
	private String name;
	private List<Integer> scoreList;
	
	public GameScores convertToGameScoreEntity(Games gameEntity, int teamIndex, int gameIndex) {
		return GameScores.builder()
			.game(gameEntity)
			.member(Members.builder().seq(seq).build())
			.teamIndex(teamIndex)
			.gameIndex(gameIndex)
			.score(scoreList.get(gameIndex))
			.build();
	}
}
