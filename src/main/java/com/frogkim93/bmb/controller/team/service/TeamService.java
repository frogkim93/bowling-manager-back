package com.frogkim93.bmb.controller.team.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.member.MemberDto;
import com.frogkim93.bmb.dto.team.MemberWithAvgDto;
import com.frogkim93.bmb.dto.team.TeamDto;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;
import com.frogkim93.bmb.model.TeamMappings;
import com.frogkim93.bmb.model.Teams;
import com.frogkim93.bmb.model.view.VMemberWithLastAvg;
import com.frogkim93.bmb.repository.TeamMappingsRepository;
import com.frogkim93.bmb.repository.TeamsRepository;
import com.frogkim93.bmb.repository.view.VMemberWithLastAvgRepository;

@Service
public class TeamService {
	@Autowired
	private HttpServletResponse httpServletResponse;

	@Autowired
	private TeamsRepository teamsRepository;
	
	@Autowired
	private TeamMappingsRepository teamMappingsRepository;
	
	@Autowired
	private VMemberWithLastAvgRepository vMemberWithLastAvgRepository;

	@Transactional
	public void createTeam(List<TeamDto> teamListDto) {
		try {
			Teams teamEntity = Teams.builder()
				.regTime(new Date())
				.build();

			teamsRepository.saveAndFlush(teamEntity);

			int teamIndex = 0;
			for (TeamDto team: teamListDto) {
				for (MemberDto member: team.getMemberList()) {
					TeamMappings teamMappingEntity = TeamMappings.builder()
						.team(teamEntity)
						.member(Members.builder().seq(member.getSeq()).build())
						.teamIndex(teamIndex)
						.build();

					teamMappingsRepository.saveAndFlush(teamMappingEntity);
				}

				teamIndex++;
			}
		} catch (Exception e) {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			throw new RuntimeException();
		}
	}

	public List<TeamDto> getTeamByMaster(int masterSeq) {
		Teams foundTeam = teamsRepository.findTop1ByOrderByRegTimeDesc();
		List<VMemberWithLastAvg> foundList = vMemberWithLastAvgRepository.findByAccount(Accounts.builder().seq(masterSeq).build());
		
		List<TeamDto> teamList = new ArrayList<TeamDto>();

		for (TeamMappings teamMapping: foundTeam.getTeamMappingList()) {
			TeamDto team = getTeamByIndex(teamList, teamMapping.getTeamIndex());

			if (team == null) {
				team = TeamDto.builder()
					.teamIndex(teamMapping.getTeamIndex())
					.memberList(new ArrayList<MemberDto>())
					.build();
				
				teamList.add(team);
			}

			VMemberWithLastAvg foundVMember = getVMember(teamMapping.getMember().getSeq(), foundList);

			if (foundVMember != null) {
				MemberWithAvgDto member = new MemberWithAvgDto(foundVMember);
				team.getMemberList().add(member);
			}
		}

		return teamList;
	}

	private TeamDto getTeamByIndex(List<TeamDto> teamList, int index) {
		if (teamList.size() == 0) {
			return null;
		}
		
		for (TeamDto team: teamList) {
			if (team.getTeamIndex() == index) {
				return team;
			}
		}
		
		return null;
	}
	
	private VMemberWithLastAvg getVMember(int memberSeq, List<VMemberWithLastAvg> list) {
		for (VMemberWithLastAvg vMember: list) {
			if (vMember.getSeq() == memberSeq) {
				return vMember;
			}
		}
		
		return null;
	}
}
