package model;

import java.security.MessageDigest;

public class Encryptor {

	public String encodePassword(String pw) {
		
		StringBuilder sb = new StringBuilder();
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA3-256");
			md.update(pw.getBytes());
			byte[] shaBytes = md.digest();
			
			for(byte b : shaBytes) {
				sb.append(String.format("%02x", b));
			}
			
		}catch (Exception e) {
			System.out.println("해시 암호화 오류: " + e.getMessage());
			return null;
		}
		
		return sb.toString();
	}
}
