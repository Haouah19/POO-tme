package pobj.motx.tme3.csp;

import java.util.List;
/**
 * Interface qui matérialise une Variable
 *
 */
public interface IVariable {
	/**
	 * Retourne l'ensemble des valeurs que la variable peut prendre 
	 * @return
	 */
	public List<String> getDomain();
}
