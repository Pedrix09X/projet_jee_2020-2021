package sql;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utils {

	public static String hashPassword(String password) throws Exception {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password.toCharArray(), new byte[1], 1000, 256));
		return Base64.encodeBase64String(key.getEncoded());
	}
	
}