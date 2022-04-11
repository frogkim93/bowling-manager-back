package com.frogkim93.bmb.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtils {
	private static RSAUtils instance = null;
	private static PublicKey publicKey;
	private static PrivateKey privateKey;
	
	public static RSAUtils getInstance() {
		if (instance == null) {
			instance = new RSAUtils();
		}
		
		return instance;
	}
	
	public void createKeyPair() {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			
			KeyPair keyPair = generator.genKeyPair();
			
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String encode(String text) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			byte[] bytes = cipher.doFinal(text.getBytes());

			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return text;
	}
	
	public String getDecodedText(String encodedText) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] bytes = Base64.getDecoder().decode(encodedText.getBytes());

			return new String(cipher.doFinal(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPublicKey() {
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}
}
