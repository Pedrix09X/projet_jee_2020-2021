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
	
	/**
	 * Permet de sauvegarder l'entité dans la base de donnée. Si l'entité est présente,
	 * cette méthode executera un update sur la table corresponante. Sinon, elle créera
	 * une nouvelle entrée dans la table.
	 * @param e Entity a ajouter
	 * @return true si l'opéation à reussi.
	 * @throws SQLException si une erreur SQL s'est produit.
	 */
	public boolean save(Entity e) throws SQLException;
}
