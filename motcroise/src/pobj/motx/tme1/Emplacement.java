package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * cette classe nous donnes les identification des emplacements des mots dans la grille.
 *
 */
public class Emplacement {
	/** liste des Cases pleines contigues */
	private List<Case> lettres;
	
	/** 
	 * Construit une liste de Cases
	*/
	public Emplacement() {
		lettres = new ArrayList<Case>();
	}
	
	/** 
	 * Ajoute un élément à la liste lettres
	 * @param c Case à ajouter à la liste des Cases 
	*/
	public void add(Case c) {
		lettres.add(c);
	}
	
	/** 
	 * Retire tous les éléments de la liste lettres
	*/
	public void clear() {
		lettres.clear();
	}
	
	/** 
	 * Retourne l'élément se trouvant à l'index spécifié
	 * @param index l'index de la Case que nous voulons retournée
	 * @return Une case de notre Emplacement
	*/
	public Case getCase(int index) {
		return lettres.get(index);
	}
	
	/**
	 * le contenu de la liste de notre Emplacement est affiché de façon lisible
	*/
	public String toString() {
		String str = "";
		for(Case c : lettres) 
			str += c.getChar();
		return str;
	}
	
	/**
	 * Accesseur Retourne la taille de la liste
	 * @return la taille de la liste 
	*/
	public int size() {
		return lettres.size();
	}
	
	/**
	 * Donne la liste des Cases
	 * @return la liste des Cases d'un Emplacement
	 */
	public List<Case> getLettres() {
		return lettres;
	}
	
	public boolean hasCaseVide() {
	for(Case c:lettres)
		if(c.isVide())
			return true;
	return false;
	}
	
	
}
