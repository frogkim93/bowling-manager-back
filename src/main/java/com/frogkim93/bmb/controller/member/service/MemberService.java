package com.frogkim93.bmb.controller.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.member.CreateMemberDto;
import com.frogkim93.bmb.dto.member.MemberDto;
import com.frogkim93.bmb.dto.member.UpdateMemberDto;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;
import com.frogkim93.bmb.repository.MembersRepository;

@Service
public class MemberService {
	@Autowired
	private HttpServletResponse httpServletResponse;

	@Autowired
	private MembersRepository membersRepository;

	public boolean createMember(CreateMemberDto createMemberDto) {
		Members entity = createMemberDto.convertToEntity();

		try {
			membersRepository.saveAndFlush(entity);
		} catch (Exception e) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

			return false;
		}

		return true;
	}

	public List<MemberDto> getMemberListByMaster(int masterSeq) {
		List<Members> entityList = membersRepository.findByMaster(Accounts.builder().seq(masterSeq).build());
		List<MemberDto> memberList = new ArrayList<MemberDto>();

		for (Members entity: entityList) {
			MemberDto member = MemberDto.builder().entity(entity).build();

			memberList.add(member);
		}

		return memberList;
	}

	public boolean updateMember(int memberSeq, UpdateMemberDto updateMemberDto) {
		Optional<Members> foundMember = membersRepository.findById(memberSeq);

		if (foundMember.isEmpty()) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

			return false;
		}

		Members memberEntity = foundMember.get();

		memberEntity.setName(updateMemberDto.getName());
		memberEntity.setGender(updateMemberDto.getGender());

		membersRepository.saveAndFlush(memberEntity);

		return true;
	}

	public boolean deleteMember(int memberSeq) {
		try {
			membersRepository.deleteById(memberSeq);
		} catch (Exception e) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

			return false;
		}

		return true;
	}
}
