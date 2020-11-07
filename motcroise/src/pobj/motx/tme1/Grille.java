package pobj.motx.tme1;
/**
* Classe de représentation de la Grille.
*/
public class Grille {
	/** Tableau à deux dimensions de Cases  */
	private Case [][] cases;
	/** hauteur & largeur de la Grille  */
	private int hauteur,largeur;
	
	/**
	* Construit une Grille de hauteur et largeur connue
	* @param hauteur de la Grille
	* @param largeur de la Grille
	*/
	public Grille( int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		cases =new Case[hauteur][largeur];
		for(int i=0;i<hauteur;i++)
			for(int j=0;j<largeur;j++)
				cases[i][j]=new Case(i, j, ' ');
		
	}
	
	/**
	* Accède à la Case se trouvant aux coordonnées (lig,col)
	* @param lig hauteur de la Case
	* @param col largeur de la Case
	* @return la Case se trouvant aux coordonnées (lig,col)
	*/
	public Case getCase(int lig,int col) {
			return cases[lig][col];
	}
	
	/**
	* le contenu de la Grille est affichée
	* 
	*/
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	
	/**
	* Accède à la hauteur de la Grille
	* @return le nombre de lignes de la Grille
	*/
	public int nbLig() {
		return hauteur;
	}
	
	/**
	* Accède à la largeur de la Grille
	* @return le nombre de colonnes de la Grille
	*/
	public int nbCol() {
		return largeur;
	}
	
	/**
	* rend une copie à l’identique de la grille courante
	* @return une Grille identique de la grille courante
	*/
	public Grille copy() {
		Grille copy = new Grille(hauteur,largeur);
		for(int i= 0; i<hauteur ; i++) {
			for(int j=0 ; j<largeur; j++) {
				copy.cases[i][j]= new Case(i, j, cases[i][j].getChar() );
			}
		}
		/*
		try {
			copy = (Grille)this.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		*/
		return copy;
	}
	
}
