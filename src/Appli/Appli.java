package Appli;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

import Domain.Jeu;

public class Appli { 
	
	public static void main (String ... args) {
		
		Jeu jeu = new Jeu();  // instance d'un nouveau jeu
		jeu.lancer();		  // lancement du jeu
		jeu.whoIsTheWinner(); // méthode de tests déterminant le gagnant
	
	}
}
