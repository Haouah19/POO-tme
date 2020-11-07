package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Une liste de lettres (caracteres)
 *
 */
public class EnsembleLettre {
	/** ArrayList qui représente la Liste des lettres */
	private List<Character> caracteres;
	
	/**
	 * Construit une liste de Character nommée caracteres
	 */
	public EnsembleLettre() {
		this.caracteres =new ArrayList<>();
	}
	
	/**
	 * Ajoute un Element à la liste si il n'est pas  présent
	 * @param c le caractére à rajouter dans la liste
	 */
	public void add(char c) {
		if(!caracteres.contains(c))
		caracteres.add(c);
	}
	
	/**
	 * retourne la taille de la liste de caracrtères
	 * @return la taille de list en variable d'instance
	 */
	public int size() {
		return caracteres.size();
	}
	
	/**
	 * Regarde si l'element donné en paramétre est présent dans la liste 
	 * @param c element supposé se trouver dans la liste
	 * @return un boolean dépendant de la présence de c dans la liste
	 */
	public boolean contains(char c) {
		return caracteres.contains(c);
	}
	
	/**
	 * remove all the array list’s elements that are not contained in the specified collection 
	 * @param c collection spécifique 
	 * @return un boolean dépendant de la réussite de l'opération
	 */
	public boolean retainAll(Collection<?> c) {
		return caracteres.retainAll(c);
	}
	
	/**
	 * construit une liste identique à celle de this
	 * @return une copie conforme de la liste de caractères
	 */
	public EnsembleLettre copy() {
		EnsembleLettre copy = new EnsembleLettre();
		for (char c : caracteres) {
			copy.add(c);
		}
		return copy;
	}
	
	/**
	 * Construit une liste de caractères qui représente l'intersection de la liste en paramètre et la liste courante  
	 * @param e liste pour l'intersection
	 * @return une liste représentant l'intersection de nos deux listes (this & la liste en paramètre)
	 */
	public EnsembleLettre intersectionEnsemble(EnsembleLettre e) {
		EnsembleLettre intersection=this.copy();
		// changement 
		intersection.caracteres.retainAll(e.caracteres);
		return intersection;
	}
}
