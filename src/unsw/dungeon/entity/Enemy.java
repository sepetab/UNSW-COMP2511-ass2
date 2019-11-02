package unsw.dungeon.entity;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.meta.Entity;
import unsw.dungeon.entity.meta.EntityLevel;
import unsw.dungeon.entity.meta.Interactable;
import unsw.dungeon.entity.meta.ItemEntity;
import unsw.dungeon.entity.meta.MovableEntity;
import unsw.dungeon.entity.meta.Usable;
import unsw.dungeon.events.LocationChanged;

public class Enemy extends MovableEntity<Enemy> implements Interactable {

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, EntityLevel.OBJECT, x, y);
	}

	private boolean move(int xDirection, int yDirection) {
		int oldX = getX();
		int oldY = getY();

		int newX = oldX + xDirection;
		int newY = oldY + yDirection;

		LocationChanged e = new LocationChanged(oldX, oldY, newX, newY);

		if (!this.moveIntent.emit(e)) {
			return false;
		}

		if (isPositionBlocked(newX, newY)) {
			return false;
		}

		this.setXY(newX, newY);
		return true;
	}

	public void setXY(int newX, int newY) {
		int oldX = getX();
		int oldY = getY();
		if (!this.getDungeon().positionIsValid(newX, newY)) {
			return;
		}

		if (oldX != newX) {
			x().set(newX);
		}
		if (oldY != newY) {
			y().set(newY);
		}

		this.moveEvent.emit(new LocationChanged(oldX, oldY, newX, newY));

	}

	public boolean moveUp() {
		return move(0, -1);
	}

	public boolean moveDown() {
		return move(0, 1);
	}

	public boolean moveLeft() {
		return move(-1, 0);
	}

	public boolean moveRight() {
		return move(1, 0);
	}

	public void kill() {
		this.hide();
		this.getDungeon().removeEntity(this);
	}

	public boolean roam(Player p) {
		int X = p.getX() - this.getX();
		int Y = p.getY() - this.getY();
		boolean moveSuccess = false;
		if (X > 0 && moveSuccess == false) {
			moveSuccess = moveRight();
		}

		if (X < 0 && moveSuccess == false) {
			moveSuccess = moveLeft();
		}

		if (Y > 0 && moveSuccess == false) {
			moveSuccess = moveDown();
		}

		if (Y < 0 && moveSuccess == false) {
			moveSuccess = moveUp();
		}

		return true;
	}

	public boolean flee(Player p) {
		int X = p.getX() - this.getX();
		int Y = p.getY() - this.getY();
		boolean moveSuccess = false;
		if (X > 0 && moveSuccess == false) {
			moveSuccess = moveLeft();
		}

		if (X < 0 && moveSuccess == false) {
			moveSuccess = moveRight();
		}

		if (Y > 0 && moveSuccess == false) {
			moveSuccess = moveUp();
		}

		if (Y < 0 && moveSuccess == false) {
			moveSuccess = moveDown();
		}

		return true;
	}

	public void playerMoveEventHandler(Player player, LocationChanged event) {
		if (player.hasItemUsable(InvincibilityPotion.class)) {
			flee(player);
		} else {
			roam(player);
		}
	}

	public boolean playerMoveIntentHandler(Player player, LocationChanged event) {
		if (this.getX() != event.newX || this.getY() != event.newY) {
			return true;
		}
		return player.interact(this);
	}

	@Override
	public boolean interact(Entity entity) {
		if (entity instanceof Player) {
			Player p = (Player) entity;

			List<ItemEntity> inv = p.getInventory();
			for (ItemEntity item : inv) {
				if (item instanceof Usable) {
					boolean result = ((Usable) item).use(this);
					if (result) {
						return true;
					}
				}
			}

			p.kill();
		}

		return false;
	}

}
