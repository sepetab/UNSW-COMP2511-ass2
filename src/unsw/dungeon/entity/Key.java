package unsw.dungeon.entity;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.meta.Entity;
import unsw.dungeon.entity.meta.EntityLevel;
import unsw.dungeon.entity.meta.Interactable;
import unsw.dungeon.entity.meta.ItemEntity;
import unsw.dungeon.entity.meta.Usable;

public class Key extends ItemEntity implements Usable {

	public Key(Dungeon dungeon, int x, int y) {
		super(dungeon, EntityLevel.ITEM, x, y);
		this.id = -1;
	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}
	@Override
	public boolean use(Interactable entity) {
		if (getUses() <= 0) {
			return false;
		}

		if (!entity.interact(this)) {
			return false;
		}

		itemUsed().emit(new ItemUsed(this.uses, --this.uses));
		return true;
	}

	@Override
	public int getUses() {
		return 0;
	@Override
	public boolean maxOne() {
		return true;
	}
	@Override
	}
}
