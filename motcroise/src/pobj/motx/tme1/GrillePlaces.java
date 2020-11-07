package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* La classe GrillePlaces doit explorer une Grille pour trouver tous les emplacements des mots qu’elle contient
*/
public class GrillePlaces {
	/** Grille représentant */
	private Grille grille;
	/** stocker les emplacements trouvés dans une List */ 
	private List<Emplacement> places;
	/** le nombre d’emplacements horizontaux détectés */
	private int nbPlacesHorizontal;
	
	/**
	 * Construit une GrillePLaces qui a pour but :
	 * d' itérer sur les lignes d'une grille donnée en paramétre, chercher les emplacements de mots, noter le nombre d’emplacements horizontaux détectés,
	 * puis itérer sur les colonnes en cherchant les emplacements verticaux.
	*/
	public GrillePlaces(Grille grille) {
		this.grille = grille;
		places = new ArrayList<Emplacement>();
		
		for(int i=0; i<grille.nbLig(); i++) {
			cherchePlaces(getLig(i));
		}
		nbPlacesHorizontal= places.size();

		for(int i=0; i<grille.nbCol(); i++) {
			cherchePlaces(getCol(i));
		}
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	/**
	 * Accesseur pour accéder aux mots détectés
	 * @return la liste d'emplacement de mot dans la Grille
	 */
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	/**
	 * Accesseur pour obtenir le nombre de mots horizontaux
	 * @return le nombre de mots horizontaux présant dans notre liste d'emplacement places
	 */
	public int getNbHorizontal() {
		return nbPlacesHorizontal;
	}
	
	/**
	 * Permet d’afficher les emplacements de mots détectés de façon lisible.
	 */
	public String toString() {
		return Arrays.toString(places.toArray());
	}
	
	/**
	 * methode qui rend les cases qui constituent une ligne 
	 * @param lig une ligne dans notre grille
	 * @return une liste de Cases se trouvant dans une même ligne
	 */
	private List<Case> getLig(int lig) {
		List<Case> res = new ArrayList<Case>();
		for(int i= 0; i<grille.nbCol() ; i++) {
			res.add(grille.getCase(lig, i));
		}
		return res;
	}

	/**
	 * methode qui rend les cases qui constituent une colonne 
	 * @param col une colonne dans notre grille
	 * @return une liste de Cases se trouvant dans une même colonne
	 */
	private List<Case> getCol(int col) {
		List<Case> res = new ArrayList<Case>();
		for(int i= 0; i<grille.nbLig() ; i++) {
			res.add(grille.getCase(i, col));
		}
		return res;
	}
	
	/**
	 * Cherche les mots dans la liste de cases fournie (une ligne ou une colonne) et qui ajoute les emplacements de mot trouvés aux places de la grille.
	 * @param cases liste de Cases pour chercher les emplacements de mots.
	 */
	private void cherchePlaces(List<Case> cases) {
		Emplacement emp = new Emplacement();
		for(int i=0; i<cases.size(); i++) {
			if(!(cases.get(i).isPleine())) {
				emp.add(cases.get(i));
			}else {
				if(emp.size() >= 2) {
					places.add(emp);
				}
			// Création d'un nouvel Objet 
			 emp = new Emplacement();
			}
		}
		// Quand on sort de la boucle, on fait le traitement sur les cases pour créer un Emplacement.
		if(emp.size() >= 2) {
			places.add(emp);
		}
		emp = new Emplacement();
	}
	
	/**
	 * rend une nouvelle grille où les cases constituant l’emplacement de mot d’indice m
	 * @param m indice du mot 
	 * @param soluce lettres 
	 * @return nouvelle GrillePotentiel avec la grille résultant de l’affectation
	 */
	public GrillePlaces fixer(int m, String soluce) {
		Grille g=grille.copy();
		Emplacement e=places.get(m);
		
		for(int i=0;i<e.size();i++) {
			int l=e.getLettres().get(i).getLig();
			int c=e.getLettres().get(i).getCol();
			
			g.getCase(l, c).setChar(soluce.charAt(i));
		}	
		return new GrillePlaces(g);
	}
	
	
	
	
	
	
	
}
