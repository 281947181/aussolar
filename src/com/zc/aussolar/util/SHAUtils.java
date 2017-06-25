package com.zc.aussolar.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHAUtils {
	public static String genDefaultPwd(String salt){
		String defaultPwd = SHA256("123456789");
		return SHA256(salt+defaultPwd);
	}
	public static String genSalt(){
		SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);
        return bytes2hex(salt);
	}
	public static String SHA256(String input){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(input.getBytes("UTF-8"));
			return bytes2hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String bytes2hex(byte[] bytes)    
    {  
        final String HEX = "0123456789ABCDEF";  
        StringBuffer sb = new StringBuffer();  
        for (byte b : bytes)  
        {  
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt((b >> 4) & 0x0f));  
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数  
            sb.append(HEX.charAt(b & 0x0f));  
        }  
        return sb.toString();  
    } 
}
