package com.frogkim93.bmb.controller.game.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		if (gameInformation.getSeq() != 0) {
			gamesRepository.deleteById(gameInformation.getSeq());
		}
		
		Games gameEntity = gamesRepository.saveAndFlush(gameInformation.convertToEntity());
		
		int teamIndex = 0;
		
		for (GameDto.Team team: gameInformation.getTeamList()) {
			for (GameDto.Member member: team.getMemberList()) {
				TeamMappings teamMapping = teamMappingsRepository.saveAndFlush(member.convertToTeamMappingEntity(0, gameEntity.getSeq(), teamIndex));
				
				for (int i = 0; i < member.getScoreList().size(); i++) {
					gameScoresRepository.saveAndFlush(member.convertToGameScoreEntity(teamMapping.getSeq(), i));
				}
			}
			
			teamIndex++;
		}
	}
	
	public List<VMemberPreMonthAvg> test() {
		Date today = new Date();
		List<VMemberPreMonthAvg> list = gameScoreBoardRepository.findByPreMonthAvg(today, 3);
		List<VMemberPreMonthAvg> result = new ArrayList<VMemberPreMonthAvg>();
		
		for (VMemberPreMonthAvg object: list) {
			System.out.println(object.getMemberSeq()); 
			System.out.println(object.getPreMonthAvg()); 
		}
		
		return result;
	}
}
