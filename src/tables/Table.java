package tables;

import java.sql.SQLException;
import java.util.List;

import entities.Entity;

public interface Table {
	/**
	 * Récupère une entité par son ID dans la table
	 * @param id de l'entité cible
	 * @return entité cible. Null si aucun résultat n'est trouvé
	 * @throws SQLException si une erreur SQL se produit
	 */
	public Entity getByID(int id) throws SQLException;
	
	/**
	 * Récupère toutes les entités de la table
	 * @return Liste de toutes les entités. Liste vide si aucun résultat n'est trouvé
	 * @throws SQLException si une erreur SQL se produit
	 */
	public List<Entity> getAll() throws SQLException;
}
