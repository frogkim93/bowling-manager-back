package com.frogkim93.bmb.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frogkim93.bmb.controller.member.service.MemberService;
import com.frogkim93.bmb.dto.member.CreateMemberDto;
import com.frogkim93.bmb.dto.member.MemberDto;
import com.frogkim93.bmb.dto.member.UpdateMemberDto;
import com.frogkim93.bmb.dto.team.MemberWithAvgDto;

@RestController
@RequestMapping(value = "member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@PostMapping
	private boolean createMember(@RequestBody CreateMemberDto createMemberDto) {
		return memberService.createMember(createMemberDto);
	}

	@GetMapping(value = "/byMaster/{masterSeq}")
	private List<MemberDto> getMemberListByMaster(@PathVariable int masterSeq) {
		return memberService.getMemberListByMaster(masterSeq);
	}
	
	@GetMapping(value = "/byMaster/{masterSeq}/withAvg")
	private List<MemberWithAvgDto> getMemberWithAvgListByMaster(@PathVariable int masterSeq) {
		return memberService.getMemberWithAvgListByMaster(masterSeq);
	}

	@PutMapping(value = "/{memberSeq}")
	private boolean updateMember(@PathVariable int memberSeq, @RequestBody UpdateMemberDto updateMemberDto) {
		return memberService.updateMember(memberSeq, updateMemberDto);
	}

	@DeleteMapping(value = "/{memberSeq}")
	private void deleteMember(@PathVariable int memberSeq) {
		memberService.deleteMember(memberSeq);
	}
}
