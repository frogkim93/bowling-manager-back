package com.frogkim93.bmb.dto.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.frogkim93.bmb.constants.GameType;
import com.frogkim93.bmb.model.GameScores;
import com.frogkim93.bmb.model.Games;
import com.frogkim93.bmb.model.TeamMappings;
import com.frogkim93.bmb.model.Teams;
import com.frogkim93.bmb.model.view.VGameScoreBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GameDto {
	private int seq;
	private Date gameDate;
	private GameType gameType;
	private int teamSeq;
	private List<Team> teamList;
	
	@Builder
	private GameDto(List<VGameScoreBoard> vGameList) {
		if (vGameList.size() > 0) {
			seq = vGameList.get(0).getSeq();
			gameDate = vGameList.get(0).getGameDate();
			gameType = vGameList.get(0).getGameType();
			
			teamList = new ArrayList<Team>();
			
			for (VGameScoreBoard vGameScoreBoard: vGameList) {
				if (teamList.size() == 0 || teamList.size() - 1 != vGameScoreBoard.getTeamIndex()) {
					Team team = new Team();
					team.memberList = new ArrayList<GameDto.Member>();
					
					teamList.add(team);
				}
				
				Team lastTeam = teamList.get(teamList.size() - 1);
				
				Member member = Member.builder()
					.vGameScoreBoard(vGameScoreBoard)
					.build();
				
				lastTeam.memberList.add(member);
			}
		}
	}
	
	public Games convertToEntity() {
		return Games.builder()
			.seq(seq)
			.team(Teams.builder().seq(teamSeq).build())
			.gameDate(gameDate)
			.gameType(gameType)
			.build();
	} 

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class Team {
		private List<Member> memberList;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class Member {
		private int teamMappingSeq;
		private String name;
		private List<Integer> scoreList;

		@Builder
		private Member(VGameScoreBoard vGameScoreBoard) {
			teamMappingSeq = vGameScoreBoard.getMember().getSeq();
			name = vGameScoreBoard.getMember().getName();
			scoreList = new ArrayList<Integer>();
			
			String[] scoreTextList = vGameScoreBoard.getScoreList().split(",");
			
			for (int i = 0; i < scoreTextList.length; i++) {
				scoreList.add(Integer.parseInt(scoreTextList[i]));
			}
		}

		public GameScores convertToGameScoreEntity(Games gameEntity, int gameIndex) {
			return GameScores.builder()
				.game(gameEntity)
				.teamMapping(TeamMappings.builder().seq(teamMappingSeq).build())
				.gameIndex(gameIndex)
				.score(scoreList.get(gameIndex))
				.build();
		}
	}
}
