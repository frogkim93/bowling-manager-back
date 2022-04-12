package com.frogkim93.bmb.dto.member;

import com.frogkim93.bmb.constants.Gender;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;

import lombok.Getter;

@Getter
public class CreateMemberDto {
	private int accountSeq;
	private String name;
	private Gender gender;
	
	public Members convertToEntity() {
		return Members.builder()
			.master(Accounts.builder().seq(accountSeq).build())
			.name(name)
			.gender(gender)
			.build();
	}
}
