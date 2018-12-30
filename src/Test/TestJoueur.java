package Test;

import static org.junit.Assert.*;

import Domain.Joueur;
import Domain.Pion;

import org.junit.Test;

public class TestJoueur {


	@Test
	public void TestPosition() {
		int [] pos1 = {1, 2}, pos2 = {2, 2};
		Pion p = new Pion(pos1, pos2);
		char bleu = 1;
		Joueur j = new Joueur(bleu , p.position1,p.position2);
		
		// test de chaque méthodes de positionnement du joueur
		assertEquals(2, j.givePos1Col());
		assertEquals(1, j.givePos1Ligne());
		assertEquals(2, j.givePos2Col());
		assertEquals(2, j.givePos2Ligne());
		
	}
}