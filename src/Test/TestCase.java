package Test;

import static org.junit.Assert.*;

import Domain.Case;


import org.junit.Test;

public class TestCase {


	@Test
	public void TestPossible() {
		Case c1 = new Case();
		c1.setPossible("Test");
		assertEquals("Test", c1.getPossibility());
	}
	
	@Test
	public void TestUsed() {
		Case c1 = new Case();
		c1.setCaseUsed(true);
		assertEquals(true,c1.isUsed());
	}	
	
	@Test
	public void TestCross() {
		Case c1 = new Case();
		c1.initDotedCase();
		assertEquals(true,c1.isACross());
	}
	
	@Test
	public void TestUsedBy() {
		Case c1 = new Case();
		String j = " j1";
		c1.setCaseUsed(j);
		assertEquals(j,c1.usedBy());
	}
}