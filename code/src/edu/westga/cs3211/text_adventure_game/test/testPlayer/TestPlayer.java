package edu.westga.cs3211.text_adventure_game.test.testPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class TestPlayer {

	@Test
	public void playerConstructorNoDamage() {
		Player newPlayer = new Player();
		
		assertEquals(10, newPlayer.getHealth());
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

}
