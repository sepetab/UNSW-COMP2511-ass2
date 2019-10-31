package unsw.dungeon;

import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Exit;
import unsw.dungeon.entity.InvincibilityPotion;
import unsw.dungeon.entity.Key;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.entity.Switch;
import unsw.dungeon.entity.Sword;
import unsw.dungeon.entity.Treasure;
import unsw.dungeon.entity.Wall;

public class GameHooks implements LoaderHook {

	@Override
	public void onLoad(Player player) {

	}

	@Override
	public void onLoad(Wall wall) {

	}

	@Override
	public void onLoad(Exit exit) {

	}
	
	@Override
	public void onLoad(Boulder boulder) {
		Dungeon d = boulder.getDungeon();
		Player p = d.getPlayer();
		p.moveIntent.register(boulder::boulderMoveIntentHandler);
	}
	
	@Override
	public void onLoad(Switch sw) {
		Dungeon d = sw.getDungeon();
		Player p = d.getPlayer();
		p.moveIntent.register(sw::switchEnterIntentHandler);
	}

	@Override
	public void onLoad(Portal portal) {
		Dungeon d = portal.getDungeon();
		Player p = d.getPlayer();
		p.moveIntent.register(portal::portalEnterIntentHandler);
	}

	@Override
	public void onLoad(Door door) {
		Dungeon d = door.getDungeon();
		Player p = d.getPlayer();
		p.moveIntent.register(door::doorEnterIntentHandler);

	}

	@Override
	public void onLoad(Treasure treasure) {
		Dungeon d = treasure.getDungeon();
		Player p = d.getPlayer();
		p.moveEvent.register(treasure.LocationChangedHandler);
	}

	@Override
	public void onLoad(Key key) {
		Dungeon d = key.getDungeon();
		Player p = d.getPlayer();
		p.moveEvent.register(key.LocationChangedHandler);
	}

	@Override
	public void onLoad(Sword sword) {
		Dungeon d = sword.getDungeon();
		Player p = d.getPlayer();
		p.moveEvent.register(sword.LocationChangedHandler);
	}

	@Override
	public void onLoad(InvincibilityPotion potion) {
		Dungeon d = potion.getDungeon();
		Player p = d.getPlayer();
		p.moveEvent.register(potion.LocationChangedHandler);
	}

	@Override
	public void postLoad(Dungeon dungeon) {
		System.out.println("Dungeon load complete");
	}
}
