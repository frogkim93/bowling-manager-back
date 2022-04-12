package com.frogkim93.bmb.controller.team.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.member.MemberDto;
import com.frogkim93.bmb.dto.team.TeamDto;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;
import com.frogkim93.bmb.model.Teams;
import com.frogkim93.bmb.model.view.VTeamWithMaster;
import com.frogkim93.bmb.repository.TeamsRepository;
import com.frogkim93.bmb.repository.view.VTeamWithMasterRepository;

@Service
public class TeamService {
	@Autowired
	private HttpServletResponse httpServletResponse;

	@Autowired
	private TeamsRepository teamsRepository;

	@Autowired
	private VTeamWithMasterRepository vTeamWithMasterRepository;

	@Transactional
	public boolean createTeam(List<TeamDto> teamListDto) {
		try {
			teamsRepository.deleteAll();
			
			for (TeamDto team: teamListDto) {
				for (MemberDto member: team.getMemberList()) {
					Teams entity = Teams.builder()
						.member(Members.builder().seq(member.getSeq()).build())
						.teamIndex(team.getTeamIndex())
						.build();
				
					teamsRepository.saveAndFlush(entity);
				}
			}
		} catch (Exception e) {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

			throw new RuntimeException();
		}

		return true;
	}

	public List<TeamDto> getTeamByMaster(int masterSeq) {
		List<VTeamWithMaster> foundTeamList = vTeamWithMasterRepository.findByMasterOrderByTeamIndexAsc(Accounts.builder().seq(masterSeq).build());
		List<TeamDto> teamList = new ArrayList<TeamDto>();

		for (VTeamWithMaster vEntity: foundTeamList) {
			if (teamList.size() == 0 || teamList.get(teamList.size() - 1).getTeamIndex() != vEntity.getTeamIndex()) {
				TeamDto team = TeamDto.builder()
					.teamIndex(vEntity.getTeamIndex())
					.memberList(new ArrayList<MemberDto>())
					.build();
				
				teamList.add(team);
			}

			MemberDto member = MemberDto.builder()
				.entity(vEntity.getMember())
				.build();
			
			teamList.get(teamList.size() - 1).getMemberList().add(member);
		}

		return teamList;
	}
}
