package pobj.tme4;

import java.util.Collection;
import java.util.List;

/**
 * Interface représentant un multi-ensemble
 */

public interface MultiSet<T> extends Collection<T> {
	/**
	 * Ajoute count occurences de l'objet e.
	 * @param e element à ajouter
	 * @param count nombre d'occurences à ajouter
	 * @return true si il y a eu un changement 
	 */

	public boolean add(T e, int count);

	/**
	 * Ajoute l'objet e.
	 * @param e element à ajouter.
	 * @return true si il y a eu un changement.
	 */
	public boolean add(T e);
	/**
	 * enlève l'objet e.
	 * @param e element à enlever.
	 * @return true si il y a eu un changement. 
	 */

	public boolean remove(Object e);
	/**
	 * enlève count occurences de l'objet e.
	 * @param e element à enlever
	 * @param count nombre d'occurences à enlever.
	 * @return true si il y a eu un changement 
	 */

	public boolean remove(Object e, int count);
	
	/**
	 * indique le nombre d’occurrences de l’objet e dans la collection
	 * @param o objet dont on compte le nombre d'occurences.
	 * @return le nombre d'occurences ,0 si l’objet n’est pas dans le multi-ensemble 
	 */
	public int count(T o);
	
	/**
	 * vide le multi-ensemble.
	 */
	public void clear();	

	/**
	 * indique la taille du multi-ensemble, c’est-à-dire la somme des nombres d’occurrences des éléments
	 * @return la taille du multi-ensemble
	 */
	public int size();

	
	/**
	 * renvoie la liste des éléments du multi-ensemble
	 * @return la liste des éléments du multi-ensemble
	 */
	public List<T> elements();
}
