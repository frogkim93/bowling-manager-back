package com.frogkim93.bmb.dto.record;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDto {
	private int seq;
	private int teamMappingSeq;
	private String name;
	private List<Integer> scoreList;
}
