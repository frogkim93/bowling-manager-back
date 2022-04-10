package com.frogkim93.bmb.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frogkim93.bmb.controller.login.service.LoginService;
import com.frogkim93.bmb.dto.login.LoginDto;

@RestController
@RequestMapping(value = "login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	private boolean login(@RequestBody LoginDto loginDto) {
		return loginService.login(loginDto);
	}
	
	@GetMapping(value = "/publicKey")
	private String getPublicKey() {
		return loginService.getPublicKey();
	}
}
