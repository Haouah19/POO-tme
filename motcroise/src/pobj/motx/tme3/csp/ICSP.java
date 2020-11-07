package pobj.motx.tme3.csp;

import java.util.List;
/**
 * Cette interface matérialise un probléme CSP
 *
 */
public interface ICSP {
	/**
	 * Accède aux variables du problème
	 * @return une list de IVariable
	 */
	public List<IVariable> getVars();
	
	/**
	 * teste si un problème est encore satisfiable
	 * @return true/false 
	 */
	public boolean isConsistent();
	
	/**
	 * affecte une des variables du problème
	 * @param vi variable 
	 * @param val
	 * @return Un nouveau problème CSP, de même nature que le précédent, mais qui compte une variable de moins.
	 */
	public ICSP assign(IVariable vi, String val);
}
