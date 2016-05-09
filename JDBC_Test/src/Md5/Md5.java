package Md5;
import java.security.MessageDigest;
/**
 * Md5 Encrypting class for password protection
 * @author Hsieh
 *
 */
public class Md5 {
	public static String md5(String str) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] barr = md.digest(str.getBytes()); // 將 byte 陣列加密
			StringBuffer sb = new StringBuffer(); // 將 byte 陣列轉成 16 進制
			for(byte cipher : barr){
				String toHexStr = Integer.toHexString(cipher & 0xff);//取末2位
				sb.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
			}
			md5 = sb.toString(); //length 32
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}
}
