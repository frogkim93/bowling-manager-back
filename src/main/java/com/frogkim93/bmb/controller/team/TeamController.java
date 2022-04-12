package com.frogkim93.bmb.controller.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frogkim93.bmb.controller.team.service.TeamService;
import com.frogkim93.bmb.dto.team.TeamDto;

@RequestMapping(value = "team")
@RestController
public class TeamController {
	@Autowired
	private TeamService teamService;

	@PostMapping
	private boolean createTeam(@RequestBody List<TeamDto> teamListDto) {
		return teamService.createTeam(teamListDto);
	}

	@GetMapping(value = "/byMaster/{masterSeq}")
	private List<TeamDto> getTeamByMaster(@PathVariable int masterSeq) {
		return teamService.getTeamByMaster(masterSeq);
	}
}
