package pobj.motx.tme2;

import java.util.ArrayList;

import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;
/**
 * Une Grille Potentiel.
 */
public class GrillePotentiel {
	/** Grille contenant une liste de ses mots */
	private GrillePlaces grille;
	/** Un dictionnaire initial  de mots */
	private Dictionnaire dicoComplet;
	/** Une liste de Dictionnaires */
	private List<Dictionnaire> motsPot;
	/** Une liste de Contraintes */
	private List<IContrainte> contraintes;
	
	private boolean isRealisable;
	
	/**
	 * Construit une liste de dictionnaires qui contiennt tous les mots potentiels de de tous les mots de  Grille
	 * et construit une liste de contraintes pour tout les mots de la grille
	 * @param grille grille contenant une liste de mots 
	 * @param dicoComplet dictionnaire initiale
	 */
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille=grille;
		this.dicoComplet=dicoComplet;
		motsPot=new ArrayList<>();
		contraintes = new ArrayList<>();
		
		for(int i=0;i<grille.getPlaces().size();i++) {
			int len=grille.getPlaces().get(i).size();
			Dictionnaire dicTemp=dicoComplet.copy();
			
			// Filtrage par longueur
			dicTemp.filtreLongueur(len);
			
			// On récupére le mot à l'emplacement i
			Emplacement e=grille.getPlaces().get(i);
			
			// Filtrage par lettre 
			for(int j=0;j< e.getLettres().size();j++) {
				
					Case c=e.getLettres().get(j);
					if(!c.isPleine() && !c.isVide())
						dicTemp.filtreParLettre(c.getChar(), j);
				
			}
			// Ajout dans la liste de Dictionnaire
			motsPot.add(dicTemp);

			}
		detectionContrainte();
		isRealisable = propage();
	}
	
	/**
	 * Regarde si un mot n'a pas de potentiel mot dans le dictionnaire
	 * @return un boolean dépendant de la réussite de l'opération
	 */
	public boolean isDead() {
			boolean dead= false;
			
			for(int i=0;i<motsPot.size() && !dead;i++)
				if(motsPot.get(i).size()==0)
					dead=true;
			
			return dead;
	}
	
	public boolean isRealisable() {
		return isRealisable;
	}
	
	/**
	 * Nous donne la liste des Dictionnaires
	 * @return la liste des Dictionnaires 
	 */
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	/**
	 * Nous donne la liste des Contraintes
	 * @return la liste des Contraintes 
	 */
	public List<IContrainte> getContraintes(){
		return contraintes;
	}
	
	public GrillePlaces getGrille(){
		return this.grille;
	}
	/**
	 * Méthode qui détecte les croisements entre les mots horizontaux et les mots verticaux 
	 */
	private void detectionContrainte() {
		// itération sur les nombres horizontaux
		for(int i=0;i<grille.getNbHorizontal();i++) {
			Emplacement e1=grille.getPlaces().get(i);
			// itération sur les nombres verticaux
			for(int j=grille.getNbHorizontal();j<grille.getPlaces().size();j++) {
				Emplacement e2=grille.getPlaces().get(j);
				// itération sur les cases du mot horizontal
				for(int k=0;k<e1.getLettres().size();k++) {
					boolean croisement =false;
					// itération sur les cases du mot vertical
					for(int l=0;l<e2.getLettres().size() && !croisement ;l++) { 
						
							// Si la case contient déjà une lettre,
							//il est inutile de construire une contrainte
							//son effet est déjà pris en compte à la construction
							if(e1.getCase(k).isVide()) {
								//on vérifie ou est le croisement
								if(e1.getCase(k).equals(e2.getCase(l))) {
									IContrainte c=new CroixContrainte(i, k, j, l);
									c.reduce(this);
									contraintes.add(c);
									croisement=true;
								}
							}
					}
				}
				
				
			}
		}
	}
	
	/**
	 * Supprime tous les mots qui ne respectent pas une contrainte 
	 * @return un boolean dépendant de la réussite de l'opération
	 */
	private boolean propage() {
		while(true) {
			int motsElimines=0;
			
			for(IContrainte c:contraintes) {
				motsElimines+=c.reduce(this);
			}
			
			if(isDead())
				return false;
			
			if(motsElimines==0)
				return true;
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i =0 ; i<grille.getGrille().nbLig();i++) {
			for(int j =0 ; j<grille.getGrille().nbCol();j++) {
				sb.append(grille.getGrille().getCase(i, j).getChar()+" \t ");
			}
			sb.append("\n ");
		}
		return sb.toString();
	}
	
	/**
	 * initialise une nouvelleGrillePotentiel avec la grille résultant de l’affectation.
	 * @param m
	 * @param soluce
	 * @return une nouvelle grille
	 */
	public GrillePotentiel fixer(int m, String soluce) {
		// Retourne une nouvelle GrillePotentiel 
		// pour que notre grillePotentiel ne soit pas modifiée par l'affectation d'un mot dans un emplacement
		return new GrillePotentiel(grille.fixer(m, soluce), dicoComplet);
	}

}

