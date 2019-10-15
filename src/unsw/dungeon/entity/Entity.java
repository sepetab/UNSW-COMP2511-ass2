package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	private IntegerProperty x, y;

	protected EntityLevel entityLevel;

	/**
	 * Create an entity positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
	}

	public IntegerProperty x() {
		return x;
	}

	public IntegerProperty y() {
		return y;
	}

	public int getY() {
		return y().get();
	}

	public int getX() {
		return x().get();
	}

	public EntityLevel getEntityLevel() {
		return this.entityLevel;
	}
}
