package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	/**
	 * l'indice de l’emplacement de mot correspondant
	 */
	private int index;
	
	/**
	 * reference d'une grillePotentiel
	 */
	private GrillePotentiel grille ;
	
	

	/**
	 * Construit un DicoVariable avec l'index de l'emplacement et une grille
	 * 
	 * @param index l'index de l'emplacement du mot
	 * @param gp    la grille
	 */
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index = index;
		this.grille = gp;
		
	}
	/**
	 * Rend les domaines 
	 * @return une liste de mots
	 */
	@Override
	public List<String> getDomain() {
		return this.grille.getMotsPot().get(index).getMots();
	}
	
	/**
	 * l'indice de l'emplacement du mot
	 * 
	 * @return l'indice de l'emplacement du mot
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Accede à la grille potentiel
	 * 
	 * @return GrillePotentiel
	 */
	public GrillePotentiel getGrille() {
		return grille;
	}
	
	/**
	 * toString() qui affiche la grille
	 */
	public String toString() {
		return "l'index de l'emplacement: " + this.index + " l'ensemble des mots: "
				  + this.grille.getMotsPot().get(this.index);
	}
 
}
