package sql;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	 * Crée une instance de Timestamp à l'aide d'une chaine de caractère comportant la date et l'heure.
	 * @param dateStr String à convertir en Timestamp
	 * @return La date et l'heure dans un objet Timestamp
	 */
	public static Timestamp getTimestampOf(String dateStr) {
		SimpleDateFormat sdf;
		if (dateStr.contains("T")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		Timestamp ts;
		try {
			ts = new Timestamp(sdf.parse(dateStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			ts = null;
		}
		
		return ts;
	}
	
	/**
	 * Génère une chaine de caractère représentant la date et l'heure de l'instance Timestamp
	 * @param ts Timestamp représentant la date et l'heure
	 * @return la date et l'heure au format "dd/MM/yyyy 'à' HH:mm"
	 */
	public static String timestampToString(Timestamp ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm");
		return sdf.format(ts);
	}
	
	/**
	 * Génère une chaine de caractère représentant la date de l'instance Date
	 * @param dt Date représentant la date
	 * @return la date et l'heure au format "dd/MM/yyyy"
	 */
	public static String dateToString(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dt);
	}
}