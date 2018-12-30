package Domain;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

public class Plateau {
	public Case[] board[] = {new Case[3],new Case[3],new Case[3]};
	// la plateau se compose de 3X3 cases
	
	
	/**
	 * Constructeur par défaut du plateau de jeu
	 */
	public Plateau() {
		for(Case cases[] : this.board) { // on parcourt les cases du plateau
			for(int z = 0; z < cases.length; z++) {
				cases[z] = new Case(); // instance de nouvelle case
				if(z + 1 == cases.length) {
					cases[z].initDotedCase(); // placement des points
				}
			}
		}
	}
	
	/**
	 * @param pos[]
	 * @param pos2[]
	 * @return true si la position est la bonne
	 */
	public boolean posIsGood (int pos[], int pos2[]) {
		if(pos[0] <= (this.board[0].length - 1) && pos[1] <= (this.board.length - 1) && pos2[0] <= (this.board[0].length - 1) && pos2[1] <= (this.board.length - 1) && pos[0] >= 0 && pos2[0] >= 0 && pos[1] >= 0 && pos2[1] >= 0) {
			return true;
		}
		return false;
	}
	/**
	 * @param pos
	 * @param pos2
	 * @return true si la position est la bonne
	 */
	public boolean posAvailable (int pos, int pos2) {
		if(pos <= (this.board.length - 1) && pos2 <= (this.board[0].length - 1) && pos >= 0 && pos2 >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * met les cases voulues, libres
	 * @param pos
	 */
	public void freeCases (int pos[][]) {
		int i = 0;
		int y = 0;
		for(Case cases[] : this.board) {
			for(int z = 0; z < cases.length; z++) 
			{
				if(!cases[z].isUsed()) 
				{
					pos[y][0] = i;
					pos[y][1] = z;
					y++;
				}
			}
		i++;
		}
	}
	
	/**
	 * @param Pos
	 * @param Pos2
	 * @return couleur
	 */
	public String getColor (int Pos, int Pos2) {
		return this.board[Pos][Pos2].usedBy();
	}
	
	/**
	 * @param Pos
	 * @param Pos2
	 * @return true si les 2 cases sont adjacentes sinon false
	 */
	public boolean estAcote (int Pos[], int Pos2[]) {
		if(this.posAvailable(Pos[0], Pos[1]) && this.posAvailable(Pos2[0], Pos2[1])) {
			if((Pos[0] - 1) == Pos2[0] && Pos[1] == Pos2[1])
			{
				return true;
			}
			else if((Pos[0] + 1) == Pos2[0] && Pos[1] == Pos2[1]) 
			{
				return true;
			}
			else if(Pos[0] == Pos2[0] && (Pos[1] - 1) == Pos2[1]) 
			{
				return true;
			}
			else if(Pos[0] == Pos2[0] && (Pos[1] + 1) == Pos2[1]) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param pos
	 * @param clr
	 * @return true si a coté sinon false
	 */
	public boolean estAcote (int pos[], String clr) {
		if(this.posAvailable(pos[0], pos[1])) {
			if(this.posAvailable((pos[0] - 1), pos[1]) && this.getColor((pos[0] - 1), pos[1]).contentEquals(clr))
			{
				return true;
			}
			else if(this.posAvailable((pos[0] + 1), pos[1]) && this.getColor((pos[0] + 1), pos[1]).contentEquals(clr)) 
			{
				return true;
			}
			else if(this.posAvailable(pos[0], (pos[1] - 1)) && this.getColor(pos[0], (pos[1] - 1)).contentEquals(clr)) 
			{
				return true;
			}
			else if(this.posAvailable(pos[0], (pos[1] + 1)) && this.getColor(pos[0], (pos[1] + 1)).contentEquals(clr)) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param pos
	 * @param clr
	 * @return {-1, -1}
	 */
	public int[] posEstAcote (int pos[], String clr) {
		if(this.posAvailable(pos[0], pos[1])) {
			if(this.posAvailable((pos[0] - 1), pos[1]) && this.getColor((pos[0] - 1), pos[1]).contentEquals(clr))
			{
				int[] retour = {(pos[0] - 1), pos[1]};
				return retour;
			}
			else if(this.posAvailable((pos[0] + 1), pos[1]) && this.getColor((pos[0] + 1), pos[1]).contentEquals(clr)) 
			{
				int[] retour = {(pos[0] + 1), pos[1]};
				return retour;
			}
			else if(this.posAvailable(pos[0], (pos[1] - 1)) && this.getColor(pos[0], (pos[1] - 1)).contentEquals(clr)) 
			{
				int[] retour = {pos[0], (pos[1] - 1)};
				return retour;
			}
			else if(this.posAvailable(pos[0], (pos[1] + 1)) && this.getColor(pos[0], (pos[1] + 1)).contentEquals(clr)) 
			{
				int[] retour = {pos[0], (pos[1] + 1)};
				return retour;
			}
		}
		int[] retour = {-1, -1};
		return retour;
	}
	
	/**
	 * @param player
	 * @param predic
	 */
	public void setPredictAndErase(String player, int predic[])
	{
		for(Case cases []: this.board) 
		{
			for(Case cases2 : cases) // parcourt chaque case
			{
				if(cases2.usedBy().contentEquals(player)) 
				{
					cases2.setCaseNotUsed(); // la case n'est pas utilisée
				}
			}
		}
		this.board[predic[0]][predic[1]].setCaseUsed(player); // les cases occupées par les joueurs sont inidquées occupées
		this.board[predic[2]][predic[3]].setCaseUsed(player);
	}
	
	/**
	 * @param joueur
	 * @return le score du joueur
	 */
	public int score(String joueur)
	{
		int scoreCount = 0;
		for(Case cases []: this.board) 
		{
			for(Case cases2 : cases) 
			{
				if(cases2.usedBy().contentEquals(joueur) && cases2.isACross()) 
				{
					scoreCount++;
				}
			}
		}
		return scoreCount;
	}
	
	/**
	 * affiche le plateau dans la console
	 */
	public void dispBoard() {
		String ligne = "*";
		
		for(int y = 1; y <= 9 * this.board.length - this.board.length; y++) {
				ligne = ligne.concat("*");
		}
		
		System.out.println(ligne);
		ligne = "";
		
		for(Case cases[] : this.board) {
			
			ligne = "*";
			
			for(int y = 0; y < this.board.length; y++) {

				ligne = ligne.concat("       *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(Case cases2 : cases) {
				
				ligne = ligne.concat(" ");
				
				if(cases2.isACross() && !cases2.isUsed() && !cases2.isPredict()) {
					ligne = ligne.concat("  " + "O" + "  ");
				}
				else if (cases2.isUsed() && !cases2.isPredict()) {
					ligne = ligne.concat("  " + cases2.usedBy() + "  ");
				}
				else if (cases2.isPredict()) {
					if(cases2.getPossibility().length() == 1) {
						ligne = ligne.concat("  " + cases2.getPossibility() + "  ");
					} else {
						ligne = ligne.concat(cases2.getPossibility());
					}
				}
				else {
					ligne = ligne.concat("     ");
				}
				
				ligne = ligne.concat(" *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(int y = 0; y < this.board.length; y++) {

				ligne = ligne.concat("       *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(int y = 1; y <= 9 * this.board.length - this.board.length; y++) {
				ligne = ligne.concat("*");
			}
		
			System.out.println(ligne);
			ligne = "";
		
		}
	}
	
	/**
	 * affiche le plateau dans la console
	 * @param Color
	 */
	public void dispBoard(String Color) {
		String ligne = "*";
		
		for(int y = 1; y <= 9 * this.board.length - this.board.length; y++) {
				ligne = ligne.concat("*");
		}
		
		System.out.println(ligne);
		ligne = "";
		
		for(Case cases[] : this.board) {
			
			ligne = "*";
			
			for(int y = 0; y < this.board.length; y++) {

				ligne = ligne.concat("       *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(Case cases2 : cases) {
				
				ligne = ligne.concat(" ");
				
				if(cases2.isACross() && !cases2.isUsed() && !cases2.isPredict() || cases2.isACross() && cases2.usedBy().contentEquals(Color) && !cases2.isPredict()) {
					ligne = ligne.concat("  " + "O" + "  ");
				}
				else if (cases2.isUsed() && !cases2.isPredict() && !cases2.usedBy().contentEquals(Color)) {
					ligne = ligne.concat("  " + cases2.usedBy() + "  ");
				}
				else if (cases2.isPredict()) {
					if(cases2.getPossibility().length() == 1) {
						ligne = ligne.concat("  " + cases2.getPossibility() + "  ");
					} else {
						ligne = ligne.concat(cases2.getPossibility());
					}
				}
				else {
					ligne = ligne.concat("     ");
				}
				
				ligne = ligne.concat(" *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(int y = 0; y < this.board.length; y++) {

				ligne = ligne.concat("       *");
			}
			
			System.out.println(ligne);
			ligne = "*";
			
			for(int y = 1; y <= 9 * this.board.length - this.board.length; y++) {
				ligne = ligne.concat("*");
			}
		
			System.out.println(ligne);
			ligne = "";
		
		}
	}
}
