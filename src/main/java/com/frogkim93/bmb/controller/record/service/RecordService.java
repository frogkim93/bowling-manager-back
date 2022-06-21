package com.frogkim93.bmb.controller.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.record.MemberDto;
import com.frogkim93.bmb.dto.record.RecordDto;
import com.frogkim93.bmb.dto.record.TeamDto;
import com.frogkim93.bmb.model.GameScores;
import com.frogkim93.bmb.model.Games;
import com.frogkim93.bmb.model.Teams;
import com.frogkim93.bmb.repository.GameScoresRepository;
import com.frogkim93.bmb.repository.GamesRepository;
import com.frogkim93.bmb.repository.TeamsRepository;

@Service
public class RecordService {
	@Autowired
	private GamesRepository gamesRepository;

	@Autowired
	private GameScoresRepository gameScoresRepository;

	@Autowired
	private TeamsRepository teamsRepository;

	public void createRecord(RecordDto record) {
		Games game = record.convertToGameEntity();
		game = gamesRepository.saveAndFlush(game);

		int teamIndex = 0;

		for (TeamDto team : record.getTeamList()) {
			for (MemberDto member : team.getMemberList()) {
				for (int i = 0; i < member.getScoreList().size(); i++) {
					GameScores gameScore = member.convertToGameScoreEntity(game, teamIndex, i);
					gameScoresRepository.saveAndFlush(gameScore);
				}
			}
		}

		teamIndex++;
	}
}
