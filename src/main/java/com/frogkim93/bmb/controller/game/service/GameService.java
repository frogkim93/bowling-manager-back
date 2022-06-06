package com.frogkim93.bmb.controller.game.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.game.GameDto;
import com.frogkim93.bmb.model.Games;
import com.frogkim93.bmb.model.TeamMappings;
import com.frogkim93.bmb.model.view.VGameScoreBoard;
import com.frogkim93.bmb.model.view.VMemberPreMonthAvg;
import com.frogkim93.bmb.repository.GameScoresRepository;
import com.frogkim93.bmb.repository.GamesRepository;
import com.frogkim93.bmb.repository.TeamMappingsRepository;
import com.frogkim93.bmb.repository.view.VGameScoreBoardRepository;

@Service
public class GameService {
	@Autowired
	private GamesRepository gamesRepository;
	
	@Autowired
	private TeamMappingsRepository teamMappingsRepository;
	
	@Autowired
	private GameScoresRepository gameScoresRepository;
	
	@Autowired
	private VGameScoreBoardRepository gameScoreBoardRepository;
	
	public GameDto getGame(int gameSeq) {
		List<VGameScoreBoard> foundGameList = gameScoreBoardRepository.findBySeq(gameSeq);
		
		GameDto result = GameDto.builder()
			.vGameList(foundGameList)
			.build();
		
		if (result.getSeq() == 0) {
			return null;
		}
		
		return result;
	}
	
	public void createGame(GameDto gameInformation) {
		Games gameEntity = gameInformation.convertToEntity();
		gameEntity = gamesRepository.saveAndFlush(gameEntity);
		
		for (GameDto.Team team: gameInformation.getTeamList()) {
			for (GameDto.Member member: team.getMemberList()) {
				for (int i = 0; i < member.getScoreList().size(); i++) {
					gameScoresRepository.saveAndFlush(member.convertToGameScoreEntity(gameEntity, i));
				}
			}
		}
	}
}
