package Domain;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

public class Joueur {
	public int score;
	public char color;
	public Pion pion;
	
	/**
	 * Constructeur par défaut de la classe Joueur
	 */
	public Joueur () {
		this.score = 0; // init le score à 0
		this.color = ' ';
	}
	
	/**
	 * Constructeur de la classe Joueur
	 * @param color
	 * @param pos1[]
	 * @param pos2[]
	 */
	public Joueur(char color, int pos1 [], int pos2 []) {
		this.score = 0; // initialisation du score à 0
		this.color = color; // on choisit la couleur au moment de l'instance
		this.pion = new Pion(pos1, pos2); // palcement du pion
	}
	
	/**
	 * @return la première position à la première ligne
	 */
	public int givePos1Ligne() {
		return pion.position1[0];
	}
	/**
	 * @return la première position à la première colonne
	 */
	public int givePos1Col() {
		return pion.position1[1];
	}
	/**
	 * @return la deuxième position à la deuxième ligne
	 */
	public int givePos2Ligne() {
		return pion.position2[0];
	}
	/**
	 * @return la deuxième position à la deuxième colonne
	 */
	public int givePos2Col() {
		return pion.position2[1];
	}
	
}
