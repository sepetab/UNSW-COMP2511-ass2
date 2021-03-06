package unsw.dungeon.entity.enemy;

import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Player;

public class RoamBehaviour implements EnemyMovementBehaviour {

	private Enemy enemy;

	public RoamBehaviour(Enemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public void move(Player p) {
		int X = p.getX() - enemy.getX();
		int Y = p.getY() - enemy.getY();
		boolean moveSuccess = false;
		if (X > 0 && moveSuccess == false) {
			moveSuccess = enemy.moveRight();
		}

		if (X < 0 && moveSuccess == false) {
			moveSuccess = enemy.moveLeft();
		}

		if (Y > 0 && moveSuccess == false) {
			moveSuccess = enemy.moveDown();
		}

		if (Y < 0 && moveSuccess == false) {
			moveSuccess = enemy.moveUp();
		}

	}
}
