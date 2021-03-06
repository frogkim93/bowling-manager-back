package com.frogkim93.bmb.dto.team;

import java.util.List;

import com.frogkim93.bmb.dto.member.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
	private int teamIndex;
	private List<MemberDto> memberList;
}
