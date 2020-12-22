package tables;

import java.sql.SQLException;
import java.util.List;

import entities.Entity;

public interface Table {
	/**
	 * Récupère une entité par son ID dans la table
	 * @param id de l'entité cible
	 * @return entité cible
	 * @throws SQLException si une erreur SQL se produit
	 */
	public Entity getByID(int id) throws SQLException;
	
	/**
	 * Récupère toutes les entités de la table
	 * @return Liste de toutes les entités
	 * @throws SQLException si une erreur SQL se produit
	 */
	public List<Entity> getAll() throws SQLException;
	
	/**
	 * Récupère les entités qui satisfont la condition key=value
	 * @param key nom de la colonne
	 * @param value valeur à vérifier
	 * @return Liste des entités cible
	 * @throws SQLException si une erreur SQL se produit
	 */
	public List<Entity> where(String key, String value) throws SQLException;
	
	/**
	 * Récupère les entités qui satisfont la condition key op value, avec op dans {=, <, >, <>, like) 
	 * @param key nom de la colonne
	 * @param op opérateur de test
	 * @param value valeur à vérifier
	 * @return Liste des entités cible
	 * @throws SQLException si une erreur SQL se produit
	 */
	public List<Entity> where(String key, String op, String value) throws SQLException;
}
