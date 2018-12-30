package Domain;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

import java.util.Scanner;

public class Jeu {
	Plateau plateau;
	Joueur j1;
	Joueur j2;
	Pion neutre;
	
	/**
	 * Constructeur par défaut du jeu
	 */
	public Jeu() {
		plateau = new Plateau(); // instance de nouveau plateau de jeu
		// (ne pas changer)
		int pos[][] = {{0,1},{0,2},{1,1},{1,2},{2,1},{2,2}}; // structure du plateau de jeu
		j1 = new Joueur('B', pos[0], pos[1]); // place le joueur 1
		j2 = new Joueur('R', pos[4], pos[5]); // place le joueur 2
		neutre = new Pion(pos[2], pos[3]);  // place le pion blanc
		plateau.board[j1.givePos1Ligne()][j1.givePos1Col()].setCaseUsed(j1.color + "");
		plateau.board[j1.givePos2Ligne()][j1.givePos2Col()].setCaseUsed(j1.color + "");
		plateau.board[j2.givePos1Ligne()][j2.givePos1Col()].setCaseUsed(j2.color + "");
		plateau.board[j2.givePos2Ligne()][j2.givePos2Col()].setCaseUsed(j2.color + "");
		plateau.board[j2.givePos1Ligne()][j2.givePos1Col()].setCaseUsed(j2.color + "");
		plateau.board[neutre.position1[0]][neutre.position1[1]].setCaseUsed("W");
		plateau.board[neutre.position2[0]][neutre.position2[1]].setCaseUsed("W");
	}
	
	public void whoIsTheWinner () {
		if(this.j1.score >= 12) {
			if(this.j2.score >= 6) {
				System.out.println("Le joueur '" + this.j1.color + "' a gagné ! (" + this.j1.score + " à " + this.j2.score + ")");
				System.out.println("Bravo, vous avez su gérer les points de l'adversaire.");
			}
			else {
				System.out.println("Le joueur '" + this.j2.color + "' a gagné ! (" + this.j2.score + " à " + this.j1.score + ")");
			}
		}
		else {
			if(this.j1.score >= 6) {
				System.out.println("Le joueur '" + this.j2.color + "' a gagné la partie ! (" + this.j2.score + " à " + this.j1.score + ")");
			}
			else {
				System.out.println("Le joueur '" + this.j1.color + "' a gagné la partie ! (" + this.j1.score + " à " + this.j2.score + ")");
			}
		}
	}
	
	public void lancer() {
		System.out.println("Bienvenue sur le 3 Dots Game !");
		System.out.println();
		System.out.println("  - Le joueur B jouera la couleur bleue.");
		System.out.println("  - Le joueur R jouera la couleur rouge.");
		System.out.println();
		this.plateau.dispBoard();
		while(this.j1.score < 12 && this.j2.score < 12) 
		{
			System.out.println("Joueur '" + this.j1.color + "' à votre tour de jouer !");
			this.predire(this.j1.color + "");
			System.out.println("Joueur '" + this.j1.color + "' déplacez le pion blanc");
			this.predire("W");
			this.j1.score += this.plateau.score(this.j1.color + "");
			System.out.println("Joueur " + this.j1.color);
			System.out.println("Points gagnés ce tour : " + this.plateau.score(this.j1.color + ""));
			System.out.println("Score total : " + this.j1.score);
			System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _\n" );
			if(this.j1.score < 12) 
			{
				System.out.println("Joueur '" + this.j2.color + "' à votre tour de jouer !");
				this.predire(this.j2.color + "");
				System.out.println("Joueur '" + this.j2.color + "' déplacez le pion blanc");
				this.predire("W");
				this.j2.score += this.plateau.score(this.j2.color + "");
				System.out.println("Joueur " + this.j2.color);
				System.out.println("Points gagnés ce tour : " + this.plateau.score(this.j2.color + ""));
				System.out.println("Score total : " + this.j2.score);
				System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _\n" );
			}
		}
	}
	
	public void predire(String clr) {
		int Caseslibres [][] = {new int[2],new int[2],new int[2]};
		this.plateau.freeCases(Caseslibres);
		int posPredic = 0;
		int[][] predictions  = {new int[4],new int[4],new int[4],new int[4]};
		int[] posTest = {0,0,0,0};
		for(int i = 0; i < Caseslibres.length; i++) 
		{
			for(int z = 0; z < Caseslibres.length; z++) 
			{
				if(z!=i) 
				{
					if(this.plateau.estAcote(Caseslibres[i], Caseslibres[z]))
					{
						posTest[0] = Caseslibres[i][0];
						posTest[1] = Caseslibres[i][1];
						posTest[2] = Caseslibres[z][0];
						posTest[3] = Caseslibres[z][1];
						if(!this.estDejaPredit(predictions, posTest)) {
							predictions[posPredic][0] = posTest[0];
							predictions[posPredic][1] = posTest[1];
							predictions[posPredic][2] = posTest[2];
							predictions[posPredic][3] = posTest[3];
							posPredic++;
							this.showPrediction2(posPredic, posTest);
						}
					}
				}
			}
			posTest[0] = this.plateau.posEstAcote(Caseslibres[i], clr)[0];
			posTest[1] = this.plateau.posEstAcote(Caseslibres[i], clr)[1];
			if(posTest[0] != -1 && posTest[1] != -1)
			{
				posTest[2] = Caseslibres[i][0];
				posTest[3] = Caseslibres[i][1];
				if(!this.estDejaPredit(predictions, posTest)) {
					predictions[posPredic][0] = posTest[0];
					predictions[posPredic][1] = posTest[1];
					predictions[posPredic][2] = posTest[2];
					predictions[posPredic][3] = posTest[3];
					posPredic++;
					this.showPrediction2(posPredic, posTest);
				}
			}
		}
		this.plateau.dispBoard(clr);
		for(Case cases []: this.plateau.board) 
		{
			for(Case cases2 : cases) 
			{
				cases2.hidePredict();
			}
		}
		this.choisirOption(predictions, posPredic, clr);
	}
	
	public boolean estDejaPredit(int[][] predictions, int pos[]) {
		for(int[] tabl : predictions) {
			if(tabl[0] == pos[0] && tabl[1] == pos[1] && tabl[2] == pos[2] && tabl[3] == pos[3] || tabl[0] == pos[2] && tabl[1] == pos[3] && tabl[2] == pos[0] && tabl[3] == pos[1]) {
				return true;
			}
		}
		return false;
	}
	
	public void showPrediction(int numPrediction, int pos[]) {
		for(int i = 0; i < pos.length; i+=2) {
			if(this.plateau.board[pos[i]][pos[i + 1]].isPredict()) 
			{
				this.plateau.board[pos[i]][pos[i + 1]].showPredict(this.plateau.board[pos[i]][pos[i + 1]].getPossibility() + " - " + numPrediction);
			}
			else 
			{
				this.plateau.board[pos[i]][pos[i + 1]].showPredict(numPrediction + "");
			}
		}
	}
	
	public void showPrediction2(int numPrediction, int pos[]) {
			if(pos[0] > pos[2] && pos[1] == pos[3] || pos[1] < pos[3] && pos[0] == pos[2])
			{
				if(this.plateau.board[pos[0]][pos[1]].isPredict()) 
				{
					if(pos[0] == pos[2]) {
						this.plateau.board[pos[0]][pos[1]].showPredict(this.plateau.board[pos[0]][pos[1]].getPossibility() + " - " + numPrediction);
					}
					else {
						this.plateau.board[pos[0]][pos[1]].showPredict(numPrediction + " - " + this.plateau.board[pos[0]][pos[1]].getPossibility());
					}
				} 
				else 
				{
					this.plateau.board[pos[0]][pos[1]].showPredict(numPrediction + "");
				}
			}
			else if (pos[0] < pos[2] && pos[1] == pos[3] || pos[1] > pos[3] && pos[0] == pos[2])
			{
				if(this.plateau.board[pos[2]][pos[3]].isPredict()) 
				{
					this.plateau.board[pos[2]][pos[3]].showPredict(this.plateau.board[pos[2]][pos[3]].getPossibility() + " - " + numPrediction);
				} 
				else 
				{
					this.plateau.board[pos[2]][pos[3]].showPredict(numPrediction + "");
				}
			}
	}
	
	public void choisirOption(int predic[][], int nbPredic, String player) {
		System.out.println("Sélectionnez un déplacement (1 à " + nbPredic + ")\n");
		Scanner sc = new Scanner(System.in);
		boolean choixCorrect = false;
		do {
			String reponse = sc.nextLine();
			switch(reponse.charAt(0))
			{
				case '1' :
					this.plateau.setPredictAndErase(player, predic[0]);
					choixCorrect = true;
					break;
				case '2' :
					this.plateau.setPredictAndErase(player, predic[1]);
					choixCorrect = true;
					break;
				case '3' :
					this.plateau.setPredictAndErase(player, predic[2]);
					choixCorrect = true;
					break;
				case '4' :
					if(nbPredic == 4) {
						this.plateau.setPredictAndErase(player, predic[3]);
						choixCorrect = true;
					} else {
						System.out.println("Entrez une valeur proposée : \n");
					}
					break;
				default :
					System.out.println("Entrez une valeur proposée : \n");
					break;
			}
		} while(choixCorrect == false);
		this.plateau.dispBoard();
	}
}
