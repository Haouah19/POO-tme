package pobj.motx.tme1;
/**
* Classe de représentation d'une Case dans une Grille.
*/
public class Case {
	/** Abscisse de la Case */
	private int lig;
	/** Ordonnée de la Case */
	private int col; 
	/** Caractère dans la Case */
	private char caractere;

	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.caractere = c;
	}
	
	/**
	* Accède a l’abscisse de la Case
	* @return l’abscisse 'lig' de la Case
	*/
	public int getLig() {
		return lig;
	}
	
	/**
	* Accède a l’ordonnée de la Case
	* @return l’ordonnée 'Col' de la Case
	*/
	public int getCol() {
		return col;
	}
	
	/**
	* Accède au caractère de la Case
	* @return le caractère 'caractere' de la Case
	*/
	public char getChar() {
		return caractere;
	}
	
	/**
	* Affecte la valeur en paramètre au caractère de la Case
	* @param c à mettre dans caractere dans la Case
	*/
	public void setChar(char c) {
		this.caractere = c;
	}
	
	/**
	* rend un boolean de l'état de la Case
	* @return un boolean true si la Case est vide, false sinon 
	*/
	public boolean isVide() {
		return caractere==' ';
	}
	
	/**
	* rend un boolean de l'état de la Case
	* @return un boolean true si la Case est pleine, false sinon 
	*/
	public boolean isPleine() {
		return caractere=='*';
	}
	
	/**
	 * Surchage de la methode Equals de la classe Object
	 * @param Un objet avec qui on vérifie l'égalité 
	 * @return un boolean dépendant de la réussite de l'opération
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (caractere != other.caractere)
			return false;
		if (col != other.col)
			return false;
		if (lig != other.lig)
			return false;
		return true;
	}
}
