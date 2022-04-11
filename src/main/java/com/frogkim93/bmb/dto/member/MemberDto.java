package com.frogkim93.bmb.dto.member;

import com.frogkim93.bmb.model.Members;

import lombok.Builder;
import lombok.Getter;

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
