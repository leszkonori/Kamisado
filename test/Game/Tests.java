package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class Tests {
	StarterFrame starterFrame;
	MyColor myColor;
	GameFrame gameFrame;
	
	@Before
	public void setUp() {
		starterFrame = new StarterFrame();
		myColor = new MyColor(Color.red);
		gameFrame = new GameFrame(true);
	}
	
	@Test
	public void testNewGameButton() {
		starterFrame.b1.doClick();
		boolean ok = false;
		if(starterFrame.gf != null) {
			
		}
	}
	
	@Test
	public void testGetColor() {
		Color c = myColor.getColor();
		int ok = 0;
		if(c.equals(Color.red)) {
			ok = 1;
		}
		Assert.assertEquals(ok, 1);
	}
	
	@Test
	public void testColorToString() {
		
		String result = myColor.toString();
		Assert.assertEquals(result, "piros");
	}
	
	@Test
	public void testBIndexi() {
		gameFrame.setBAktualis(gameFrame.getFields()[1][1]);
		Assert.assertEquals(gameFrame.bIndex_i(gameFrame.getButton()), 1);
	}
	
	@Test
	public void testRemoveButtonActionListener() {
		gameFrame.setBAktualis(gameFrame.getFields()[1][1]);
		gameFrame.getButton().addActionListener(gameFrame.new FieldButtonActionListener());
		gameFrame.removeButtonActionListener(gameFrame.getButton());
		boolean empty = false;
		if(gameFrame.getButton().getActionListeners().length == 0) {
			empty = true;
		}
		Assert.assertEquals(empty, true);
	}
	
	@Test
	public void testStartingButtonActionListener() {
		gameFrame.getFields()[7][0].doClick();
		boolean ok = false;
		if(gameFrame.getButton() == gameFrame.getFields()[7][0]) {
			ok = true;
		}
		Assert.assertEquals(ok, true);
	}
	
	@Test
	public void testUserMoves() {
		gameFrame.getFields()[7][0].doClick();
		gameFrame.getFields()[5][0].doClick();
		
		boolean ok = false;
		if(gameFrame.getFields()[5][0].getIcon() != null) {
			ok = true;
		}
		Assert.assertEquals(ok, true);
	}
	
	@Test
	public void testUserMoves2() {
		gameFrame.getFields()[7][0].doClick();
		gameFrame.getFields()[5][1].doClick();
		
		boolean ok = false;
		if(gameFrame.getFields()[5][1].getIcon() == null) {
			ok = true;
		}
		Assert.assertEquals(ok, true);
	}
	
	@Test
	public void testReadyButton1() {
		gameFrame.getFields()[7][0].doClick();
		gameFrame.getFields()[5][0].doClick();
		JButton prevUj = gameFrame.getUj();
		gameFrame.ready.doClick();
		
		boolean ok = false;
		if(gameFrame.getUserFields().contains(prevUj)) {
			ok = true;
		}
		Assert.assertEquals(ok, true);
	}
	
	@Test
	public void testSaveButton() {
		gameFrame.getFields()[7][0].doClick();
		gameFrame.getFields()[5][0].doClick();
		gameFrame.ready.doClick();
		gameFrame.saveButton.doClick();
		
		boolean ok = false;
		if(gameFrame.message.getText().equals("Játékállás elmentve")) {
			ok = true;
		}
		
		Assert.assertEquals(ok, true);
	}
	
	@Test
	public void testRobotMoves() {
		gameFrame.getFields()[7][0].doClick();
		gameFrame.getFields()[5][0].doClick();
		gameFrame.ready.doClick();
		
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			if(gameFrame.getRobotFields().contains(gameFrame.getFields()[0][i])) {
				counter++;
			}
		}
		Assert.assertEquals(counter, 7);
	}
}
