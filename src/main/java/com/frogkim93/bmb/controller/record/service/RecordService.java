package com.frogkim93.bmb.controller.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.controller.team.service.TeamService;
import com.frogkim93.bmb.dto.record.RecordDto;
import com.frogkim93.bmb.dto.team.TeamDto;
import com.frogkim93.bmb.repository.TeamsRepository;

@Service
public class RecordService {
	@Autowired
	private TeamsRepository teamsRepository;
	
	public RecordDto getRecentTeamForRecord(int masterSeq) {
		List<TeamDto> foundTeamList = teamService.getTeamByMaster(masterSeq);

		return null;
	}
}
