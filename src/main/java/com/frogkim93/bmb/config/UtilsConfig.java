package com.frogkim93.bmb.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.frogkim93.bmb.utils.RSAUtils;

@Configuration
public class UtilsConfig {
	@PostConstruct
	private void setRSAUtils() {
		RSAUtils rsaUtils = RSAUtils.getInstance();
		rsaUtils.createKeyPair();
		
		rsaUtils.getPublicKey();
	}
}
