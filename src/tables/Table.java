package tables;

import java.util.List;

import entities.Entity;

public interface Table {
	public Entity getByID(int id);
	public List<Entity> getAll();
	public List<Entity> where(String key, String value);
	public List<Entity> where(String key, String op, String value);
}
