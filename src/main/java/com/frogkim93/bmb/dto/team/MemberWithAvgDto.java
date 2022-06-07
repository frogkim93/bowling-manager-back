package com.frogkim93.bmb.dto.team;

import com.frogkim93.bmb.dto.member.MemberDto;
import com.frogkim93.bmb.model.view.VMemberWithLastAvg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberWithAvgDto extends MemberDto {
	private int lastAvg;
	
	public MemberWithAvgDto(VMemberWithLastAvg vEntity) {
		setSeq(vEntity.getSeq());
		setName(vEntity.getMember().getName());
		setGender(vEntity.getMember().getGender().ordinal());
		lastAvg = vEntity.getLastAvg() == null? 0: vEntity.getLastAvg().intValue();
	}
}
