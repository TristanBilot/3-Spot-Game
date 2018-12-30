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
	 * Constructeur par d�faut de la classe Joueur
	 */
	public Joueur () {
		this.score = 0; // init le score � 0
		this.color = ' ';
	}
	
	/**
	 * Constructeur de la classe Joueur
	 * @param color
	 * @param pos1[]
	 * @param pos2[]
	 */
	public Joueur(char color, int pos1 [], int pos2 []) {
		this.score = 0; // initialisation du score � 0
		this.color = color; // on choisit la couleur au moment de l'instance
		this.pion = new Pion(pos1, pos2); // palcement du pion
	}
	
	/**
	 * @return la premi�re position � la premi�re ligne
	 */
	public int givePos1Ligne() {
		return pion.position1[0];
	}
	/**
	 * @return la premi�re position � la premi�re colonne
	 */
	public int givePos1Col() {
		return pion.position1[1];
	}
	/**
	 * @return la deuxi�me position � la deuxi�me ligne
	 */
	public int givePos2Ligne() {
		return pion.position2[0];
	}
	/**
	 * @return la deuxi�me position � la deuxi�me colonne
	 */
	public int givePos2Col() {
		return pion.position2[1];
	}
	
}
