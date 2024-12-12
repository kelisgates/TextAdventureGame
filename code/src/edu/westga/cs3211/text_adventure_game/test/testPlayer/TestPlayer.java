package edu.westga.cs3211.text_adventure_game.test.testPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class TestPlayer {

	@Test
	public void playerConstructorNoDamage() {
		Player newPlayer = new Player();
		
		assertEquals(10, newPlayer.getHealth());
	}
	
	@Test
	public void testPlayerAddHealth() {
		Player player = new Player();
		
		assertEquals(10, player.getHealth());
		player.addHealth(2);
		assertEquals(12, player.getHealth());
	}
	
	@Test
	public void playerHitDamangeHardcoded() {
		Player newPlayer = new Player();
		newPlayer.reduceHealth(2);
		
		assertEquals(8, newPlayer.getHealth());
	}
	
	@Test
	public void playerHitDamageHazard() {
		Player newPlayer = new Player();
		
		String name = "DragonHazard";
		String description = "The dragon breathes fire towards you!";
		int damage = 7;
		Hazard newHazard = new Hazard(name, description, damage);
		
		newPlayer.reduceHealth(newHazard.getHazardDamageValue());
		assertEquals(3, newPlayer.getHealth());
	}
	
	
	@Test
	public void playerHitDamageMultiHazardZeroHP() {
		Player newPlayer = new Player();
		
		String name = "DragonHazard";
		String description = "The dragon breathes fire towards you!";
		int damage = 4;
		Hazard newHazard = new Hazard(name, description, damage);

		
		newPlayer.reduceHealth(newHazard.getHazardDamageValue());
		newPlayer.reduceHealth(newHazard.getHazardDamageValue());
		newPlayer.reduceHealth(newHazard.getHazardDamageValue());
		
		assertEquals(0, newPlayer.getHealth());
		assertEquals(true, newPlayer.isDead());
	}
	
	@Test
	public void testPlayerResetHealth() {
		Player player = new Player();
		
		assertEquals(10, player.getHealth());
		
		player.reduceHealth(4);
		assertEquals(6, player.getHealth());
		
		player.resetHealth();
		assertEquals(10, player.getHealth());
	}
	
	@Test
	public void testPlayerIsDeadFalseFullHealth() {
		Player player = new Player();
		
		assertEquals(false, player.isDead());
	}
	
	@Test
	public void testPlayerIsDeadFalseSingleHealth() {
		Player player = new Player();
		
		player.reduceHealth(9);
		
		assertEquals(false, player.isDead());
	}
	
	@Test
	public void testPlayerIsDead() {
		Player player = new Player();
		
		player.reduceHealth(10);
		
		assertEquals(true, player.isDead());
	}
	
	@Test
	public void testPlayerInitialLives() {
		Player player = new Player();
		
		assertEquals(3, player.getLives());
	}
	
	@Test
	public void testPlayerLoseSingleLife() {
		Player player = new Player();
		
		player.loseLife();
		assertEquals(2, player.getLives());
	}
	
	@Test
	public void testPlayerHasLivesInitial() {
		Player player = new Player();
		
		assertEquals(true, player.hasLives());
	}
	
	@Test
	void testPlayerHasLivesTwoLives() {
		Player player = new Player();
		
		player.loseLife();
		assertEquals(true, player.hasLives());
	}
	
	@Test
	void testPlayerHasLivesSingleLive() {
		Player player = new Player();
		
		player.loseLife();
		player.loseLife();
		assertEquals(true, player.hasLives());
	}
	
	@Test
	public void testPlayerHasLivesNone() {
		Player player = new Player();
		
		player.loseLife();
		player.loseLife();
		player.loseLife();
		
		assertEquals(false, player.hasLives());
	}
	
	@Test
	public void testPlayerHasQuestFalse() {
		Player player = new Player();
		
		assertEquals(false, player.hasGivenQuest());
	}
	
	@Test
	public void testPlayerHasQuestTrue() {
		Player player = new Player();
		
		player.setGivenQuest(true);
		
		assertEquals(true, player.hasGivenQuest());
	}
	
	@Test
	public void testPlayerGetInventoryEmpty() {
		Player player = new Player();
		
		assertEquals(0, player.getInventory().getSize());
	}
	
	@Test
	public void testPlayerInventoryAddItem() {
		Player player = new Player();
		Item item = new Item("BlueGem", "A radiant blue gemstone.");
		
		player.getInventory().addItem(item);
		assertEquals(1, player.getInventory().getSize());
	}
}
