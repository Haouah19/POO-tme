package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.*;
/**
 * 	Cette classe represente un objet MotX qui implemente ICSP
 */
public class MotX implements ICSP {
	/** liste de dicoVariable */
	private List<IVariable> dico;
	/** GrillePotentiel pour */
	private GrillePotentiel gp;
	
	/**
	 * initialise une liste de DicoVariable, stockée dans un attribut dico.
	 * @param gp GrilePotentiel 
	 */
	public MotX(GrillePotentiel gp) {
		this.gp = gp;
		dico = new ArrayList<>();
		
		for(int i=0 ; i< gp.getGrille().getPlaces().size(); i++) {
			if(gp.getGrille().getPlaces().get(i).hasCaseVide()) {
				dico.add(new DicoVariable(i,gp));
			}
		}
	}
	
	/**
	 * tostring()
	 */
	@Override
	public String toString() {
		return gp.toString();
	}
	
	/**
	 * retourne l'attribut qui stocke les dicoVariables
	 * @return dico la liste de dicoVariable
	 */
	@Override
	public List<IVariable> getVars() {
		return dico;
	}
	
	/**
	 * teste si un problème est encore satisfiable
	 * @return true/false 
	 */
	@Override
	public boolean isConsistent() {
		return gp.isRealisable();
	}

	/**
	 * affecte une des variables du problème
	 * @param vi variable 
	 * @param val
	 * @return Un nouveau problème CSP, de même nature que le précédent, mais qui compte une variable de moins.
	 */
	@Override
	public ICSP assign(IVariable vi, String val) {
		if(!(vi instanceof DicoVariable)) {
			return null;
		}
		gp = gp.fixer(((DicoVariable)vi).getIndex(), val);
		return new MotX(gp);
	}

}
