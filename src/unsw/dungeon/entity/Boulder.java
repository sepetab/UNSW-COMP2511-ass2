package unsw.dungeon.entity;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.meta.Entity;
import unsw.dungeon.entity.meta.EntityLevel;
import unsw.dungeon.entity.meta.Interactable;
import unsw.dungeon.entity.meta.MovableEntity;
import unsw.dungeon.events.LocationChanged;

public class Boulder extends MovableEntity<Boulder> implements Interactable {

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, EntityLevel.OBJECT, x, y);
	}

	/**
	 * Attempt to move the Boulder by the given offsets
	 * 
	 * @param xDirection
	 * @param yDirection
	 * @return result
	 */
	@Override
	protected boolean move(int xDirection, int yDirection) {
		int oldX = getX();
		int oldY = getY();

		int newX = oldX + xDirection;
		int newY = oldY + yDirection;

		LocationChanged e = new LocationChanged(oldX, oldY, newX, newY);

		if (!this.moveIntent.emit(e)) {
			return false;
		}

		// If there is an entity blocking the path...
		if (isPositionBlocked(newX, newY)) {
			Entity obstruction = this.getDungeon().getEntityAt(EntityLevel.OBJECT, newX, newY);

			// Kill the enemy if they are in the way
			if (obstruction instanceof Enemy) {
				((Enemy) obstruction).kill();
			} else {
				// Otherwise, prevent movement
				return false;
			}
		}

		this.setXY(newX, newY);
		return true;
	}

	/**
	 * Interact with the Player
	 */
	@Override
	public boolean interact(Entity entity) {
		if (!(entity instanceof Player)) {
			return false;
		}

		return move(this.getX() - entity.getX(), this.getY() - entity.getY());
	}

	public boolean playerMoveIntentHandler(Player player, LocationChanged event) {
		if (this.getX() != event.newX || this.getY() != event.newY) {
			return true;
		}
		return player.interact(this);
	}

}
