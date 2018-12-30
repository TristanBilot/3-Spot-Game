package Domain;

/**
 * @author Julien Reiss, Tristan Bilot
 * @version 1.0
 */

public class Case {
	
	private boolean used;
	private boolean doted;
	private String usedBy;
	private String possible;
	private Boolean predictShow;

	
	/**
	 * Constructeur par défaut de la case
	 */
	public Case() {
		this.usedBy = "";
		this.possible = "";
		this.doted = false;
		this.used = false;
		this.predictShow = false;
	}
	
	
	
	/**
	 * getter possible
	 * @return possible s'il est possible de 
	 * se placer sur cette case
	 */
	public String getPossibility() {
		return this.possible;
	}
	
	
	/**
	 * @param s
	 */
	public void setPossible(String s) {
		this.possible = s;
	}
	
	/**
	 * Initialise une case avec un dot
	 */
	public void initDotedCase() {
		this.doted = true;
		this.used = false;
	}
	
	/**
	 * Inidque la disponibilité d'une case
	 * @return true si la case est utilisée
	 */
	public boolean isUsed() {
		return this.used;
	}
	
	
	
	public String getUsedBy() {
		return usedBy;
	}



	public void setUsedBy(String usedBy) {
		this.usedBy = usedBy;
	}



	public void setUsed(boolean used) {
		this.used = used;
	}



	/**
	 * Indique si la case est pointée
	 * @return true si la case est doted
	 */
	public boolean isACross() {
		return this.doted;
	}
	
	/**
	 * Met la case dans l'état 'utilisée'
	 * @param b la case utilisée (true) ou non (false)
	 */
	public void setCaseUsed(boolean b) {
		this.used = b;
		if(!b) {
			this.usedBy = "\0";
		}
	}
	/**
	 * Met la case d'un joueur sans l'état utilisée
	 * @param player le joueur cible
	 */
	public void setCaseUsed(String player) {
		this.used = true;
		this.usedBy = player;
	}
	
	/**
	 * Met la case dans l'état "libre"
	 */
	public void setCaseNotUsed() {
		this.used = false;
		this.usedBy = "/0";
	}
	
	/**
	 * @return usedBy par qui la case est utilisée
	 */
	public String usedBy() {
		return "" + this.usedBy;
	}
	
	/**
	 * permet l'affichage de la case possible
	 * @param numPredic
	 */
	public void showPredict(String numPredic) {
		this.predictShow = true;
		this.possible = numPredic;
	}
	
	/**
	 * cache la case s'il n'est pas possible
	 */
	public void hidePredict() {
		this.predictShow = false;
		this.possible = "";
	}
	
	/**
	 * @return si la case est prédite
	 */
	public boolean isPredict() {
		return this.predictShow;
	}
	
	
}
