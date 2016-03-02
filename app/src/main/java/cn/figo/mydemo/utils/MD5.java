package cn.figo.mydemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(data);
		return md5.digest();
	}

	/**
	 * MD5加密32位的加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptMD5(String data) {

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(data.getBytes());
			byte b[] = md5.digest();

			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return data;
	}

}
