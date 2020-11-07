package pobj.motx.tme2;
/**
 * Interface représentant une contrainte
 */
public interface IContrainte {
	
	/**
	 * Donne le nombre de mots en moins dans notre grille après avoir effectué un filtre.
	 * @param grille en paramètre
	 * @return le nombre de mots enlevés 
	 */
	int reduce(GrillePotentiel grille);
}
