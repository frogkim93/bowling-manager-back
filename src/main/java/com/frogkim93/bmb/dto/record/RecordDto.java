package com.frogkim93.bmb.dto.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.frogkim93.bmb.constants.GameType;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Games;
import com.frogkim93.bmb.model.TeamMappings;
import com.frogkim93.bmb.model.Teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecordDto {
	private int accountSeq;
	private Date date;
	private Boolean isRegularGame;
	private List<TeamDto> teamList;
	
	@Builder
	private RecordDto(Teams teamEntity) {
		date = new Date();
		isRegularGame = true;

		teamList = new ArrayList<TeamDto>();

		for (TeamMappings teamMapping: teamEntity.getTeamMappingList()) {
			if (teamList.size() == 0) {
				TeamDto team = new TeamDto();
				team.setMemberList(new ArrayList<MemberDto>());
				
				teamList.add(team);
			}
		}
	}
	
	public Games convertToGameEntity() {
		return Games.builder()
			.account(Accounts.builder().seq(accountSeq).build())
			.gameDate(date)
			.gameType(isRegularGame? GameType.REGULAR: GameType.NOT_REGULAR)
			.build();
	}
}
