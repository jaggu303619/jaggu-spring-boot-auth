package com.jaggu.spring.boot.auth.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.springframework.security.crypto.password.PasswordEncoder;

public class JagguPasswordEncoder implements PasswordEncoder {

	private static final String SECRET = "9d40afd767c25d02af01bcb5c95689aa";
	private static final byte[] SALT = JagguPasswordEncoder.hexStringToByteArray(SECRET);

	@Override
	public String encode(CharSequence password) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(SALT);
			byte[] bytes = md.digest(password.toString().getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword.toString()).equals(encodedPassword);
	}

	/* s must be an even-length string. */
	private static final byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// Add salt
	private static String secretGenerator() throws NoSuchAlgorithmException, NoSuchProviderException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < salt.length; i++) {
			sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		System.out.println(secretGenerator());
		System.out.println(new JagguPasswordEncoder().encode("123123"));
	}

}
