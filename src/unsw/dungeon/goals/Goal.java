package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;

public class Goal {

	private GoalStrategy strategy;
	private Dungeon dungeon;

	public Goal(Dungeon dungeon, GoalStrategy strategy) {
		this.dungeon = dungeon;
		this.strategy = strategy;
	}

	/**
	 * @return Goal achieved status
	 */
	public boolean achieved() {
		return this.strategy.achieved(this);
	}

	/**
	 * @return Dungeon
	 */
	public Dungeon getDungeon() {
		return this.dungeon;
	}
}
