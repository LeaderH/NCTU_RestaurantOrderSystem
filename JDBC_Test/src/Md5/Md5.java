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
			byte[] barr = md.digest(str.getBytes()); // �N byte �}�C�[�K
			StringBuffer sb = new StringBuffer(); // �N byte �}�C�ন 16 �i��
			for(byte cipher : barr){
				String toHexStr = Integer.toHexString(cipher & 0xff);//����2��
				sb.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
			}
			md5 = sb.toString(); //length 32
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}
}
