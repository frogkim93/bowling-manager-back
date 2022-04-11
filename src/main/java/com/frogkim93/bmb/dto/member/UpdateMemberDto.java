package com.frogkim93.bmb.dto.member;

import com.frogkim93.bmb.constants.member.Gender;

import lombok.Getter;

@Getter
public class UpdateMemberDto {
	private String name;
	private Gender gender;
}
