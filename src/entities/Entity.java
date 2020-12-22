package entities;

import java.sql.SQLException;

public interface Entity {
	/**
	 * Permet de sauvegarder l'entité dans la base de donnée. Si l'entité est présente,
	 * cette méthode executera un update sur la table corresponante. Sinon, elle créera
	 * une nouvelle entrée dans la table.
	 * @return true si l'opéation à reussi.
	 * @throws SQLException si une erreur SQL s'est produit.
	 */
	public boolean save() throws SQLException;
}
