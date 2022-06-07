package com.frogkim93.bmb.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberWithScoreDto extends MemberDto {
	private int avg;
	private int avg5;
}
