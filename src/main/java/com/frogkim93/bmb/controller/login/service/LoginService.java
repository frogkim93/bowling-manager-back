package com.frogkim93.bmb.controller.login.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.frogkim93.bmb.dto.login.AccountDto;
import com.frogkim93.bmb.dto.login.LoginDto;
import com.frogkim93.bmb.dto.login.PublicKeyDto;
import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.repository.AccountsRepository;
import com.frogkim93.bmb.utils.RSAUtils;

@Service
public class LoginService {
	@Autowired
	private HttpServletResponse httpServletResponse;

	@Autowired
	private AccountsRepository accountsRepository;

	public AccountDto login(LoginDto loginDto) {
		Accounts foundAccount = accountsRepository.findByUserId(loginDto.getUserId());

		if (foundAccount == null) {
			httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}

		RSAUtils rsaUtils = RSAUtils.getInstance();
		String decodedText = rsaUtils.getDecodedText(loginDto.getPassword());

		if (decodedText == null) {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}

		if (!BCrypt.checkpw(decodedText, foundAccount.getPassword())) {
			httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		
		return AccountDto.builder()
			.seq(foundAccount.getSeq())
			.build();
	}

	public PublicKeyDto getPublicKey() {
		RSAUtils rsaUtils = RSAUtils.getInstance();

		PublicKeyDto publicKey = new PublicKeyDto();
		publicKey.setKey(rsaUtils.getPublicKey());

		return publicKey;
	}
}
