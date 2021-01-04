package sql;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utils {

	/**
	 * Permet de hacher un mot de passe si celui-ci n'est pas vide. C'est utilisé pour le stockage de mot de passe.
	 * @param password Mot de passe à hacher
	 * @return le mot de passe haché
	 * @throws Exception si une erreur se produit lors du processus de hache
	 */
	public static String hashPassword(String password) throws Exception {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password.toCharArray(), new byte[1], 1000, 256));
		return Base64.encodeBase64String(key.getEncoded());
	}
	
	/**
	 * Convertis une instance de java.util.Date en instance de java.sql.Date
	 * @param utilDate de instance java.util.Date à convertir
	 * @return une instance de java.sql.Date
	 */
	public static java.sql.Date convertUtilToSqlDate(java.util.Date utilDate) {
		Instant instant = utilDate.toInstant();
		ZoneId zoneId = ZoneId.of("GMT+1");
		ZonedDateTime zdt = ZonedDateTime.ofInstant(instant , zoneId);
		LocalDate localDate = zdt.toLocalDate();
		return java.sql.Date.valueOf(localDate);
	}
}