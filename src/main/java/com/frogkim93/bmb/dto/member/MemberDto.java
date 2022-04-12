package com.frogkim93.bmb.dto.member;

import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDto {
	private int seq;
	private String name;
	private int gender;

	@Builder
	public MemberDto(Members entity) {
		seq = entity.getSeq();
		name = entity.getName();
		gender = entity.getGender().ordinal();
	}
}
