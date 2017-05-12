package cyf.userinfo.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @author 	  宿继申
 * @DateTime 2015年7月24日 下午5:35:25
 */
public final class MD5Util {
	
	/**
	 * 加密
	 * @author 	  宿继申
	 * @DateTime 2015年7月24日 下午5:35:53
	 * @param plainText
	 * @param num
	 * @return
	 */
	public static String md5(String plainText, int num) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(StringUtil.objToString(plainText).getBytes("utf-8"));
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (num == 16) {
			return buf.toString().substring(8, 24);
		} else {
			return buf.toString();
		}

	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];

	}

	public static String getMD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultString;
	}
}
