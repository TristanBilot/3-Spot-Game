package Domain;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

public class Pion {

	public int position1[]; //[0] --> ligne, [1] colonne
	public int position2[]; //[0] --> ligne, [1] colonne
	
	/**
	 * @brief Constructeur de la classe Pion
	 * @param position1
	 * @param position2
	 */
	public Pion(int position1 [], int position2 []) {
		// instancie un pion à la position voulue
		this.position1 = position1;
		this.position2 = position2;
	}
}
