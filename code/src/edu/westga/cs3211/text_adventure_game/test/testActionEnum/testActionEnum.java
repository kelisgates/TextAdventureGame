package edu.westga.cs3211.text_adventure_game.test.testActionEnum;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;

public class testActionEnum {

	@Test
	public void testActionEnumToString() {
		assertEquals("Move North", Actions.NORTH.toString());
		assertEquals("Move East", Actions.EAST.toString());
		assertEquals("Move South", Actions.SOUTH.toString());
		assertEquals("Move West", Actions.WEST.toString());
		assertEquals("Fight", Actions.FIGHT.toString());
		assertEquals("Interact", Actions.INTERACT.toString());
		assertEquals("Pick Up", Actions.PICK_UP.toString());
	}
	
	@Test
	public void testActionEnumIndexValues() {
		int NorthValue = Actions.NORTH.getIndexValue();
		int EastValue = Actions.EAST.getIndexValue();
		int SouthValue = Actions.SOUTH.getIndexValue();
		int WestValue = Actions.WEST.getIndexValue();
		int FightValue = Actions.FIGHT.getIndexValue();
		int InteractValue = Actions.INTERACT.getIndexValue();
		int PickUpValue = Actions.PICK_UP.getIndexValue();
		
		
		assertEquals(Actions.NORTH, Actions.getActionByIndex(NorthValue));
		assertEquals(Actions.EAST, Actions.getActionByIndex(EastValue));
		assertEquals(Actions.SOUTH, Actions.getActionByIndex(SouthValue));
		assertEquals(Actions.WEST, Actions.getActionByIndex(WestValue));
		assertEquals(Actions.FIGHT, Actions.getActionByIndex(FightValue));
		assertEquals(Actions.INTERACT, Actions.getActionByIndex(InteractValue));
		assertEquals(Actions.PICK_UP, Actions.getActionByIndex(PickUpValue));
		assertNull(Actions.getActionByIndex(-1));
	}
	
	@Test
	public void testActionEnumDynamicAction() {
		String expected = "Pick Up Key";
		
		assertEquals(expected, Actions.getDynamicAction("Pick Up", "Key"));
	}
}
