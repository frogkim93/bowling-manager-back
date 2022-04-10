package com.frogkim93.bmb.controller.login.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.login.LoginDto;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.repository.AccountsRepository;
import com.frogkim93.bmb.utils.RSAUtils;

@Service
public class LoginService {
	@Autowired
	private HttpServletResponse httpServletResponse;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	public boolean login(LoginDto loginDto) {
		Accounts findedAccount = accountsRepository.findByUserId(loginDto.getUserId());
		
		if (findedAccount == null) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			
			return false;
		}
		
		/* 
		 * 임시 코드 
		 * 일반적인 텍스트로 받은 비밀번호를 암호화해주는 작업
		 * 
		 * 추후 삭제 예정
		 * */
		RSAUtils rsaUtils = RSAUtils.getInstance();
		String encodedText = rsaUtils.encode(loginDto.getPassword());
		
		String decodedText = rsaUtils.getDecodedText(encodedText);
		
		if (decodedText == null) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			
			return false;
		}
		
		if (!BCrypt.checkpw(decodedText, findedAccount.getPassword())) {
			httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			return false;
		}
		
		return true;
	}
	
	public String getPublicKey() {
		RSAUtils rsaUtils = RSAUtils.getInstance();
		return rsaUtils.getPublicKey();
	}
}
