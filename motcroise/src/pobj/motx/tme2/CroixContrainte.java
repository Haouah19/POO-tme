package pobj.motx.tme2;
/**
 * CroixContrainte implémente IContrainte
 */

public class CroixContrainte implements IContrainte{
	/** quatre attributs entiers pour stocker les indices ((m1 , c1),(m2 , c2)) */
	private int m1,m2,c1,c2;
	
	/** constructeur à quatre arguments qui initialise une croix Contrainte et ces attributs 
	 * @param m1 index du premier mot
	 * @param c1 emplacement du croisement du premier mot
	 * @param m2 index du second mot
	 * @param c2 emplacement du croisement du second mot
	 */
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1 = m1;
		this.m2 = m2;
		this.c1 = c1;
		this.c2 = c2;
	}
	
	/**
	 * Donne le nombre de mots en moins dans notre grille après avoir effectué un filtre.
	 * @param grille en paramètre
	 * @return le nombre de mots enlevés 
	 */
	@Override
	public int reduce(GrillePotentiel grille) {
		
		int reduce = 0;
		// on récupére les mots potentiels pour l'emplacement m1 & m2
		Dictionnaire dicom1 = grille.getMotsPot().get(m1);
		Dictionnaire dicom2 = grille.getMotsPot().get(m2);
		
		// Calcul des ensembles l1 & l2 pouvant figurer dans les cases c1 & c2 des emplacements m1 & m2 
		EnsembleLettre l1 = dicom1.calculeEnsemble(c1);
		EnsembleLettre l2 = dicom2.calculeEnsemble(c2);
		
		// Calcul de l'intersection (l1 AND l2)
		EnsembleLettre s  =l1.intersectionEnsemble(l2);
		
		
		// filtrer le dictionnaire par rapport aux indices c1 & c2 et l'EnsembleLettre final
	 	if(l1.size() > s.size()) {
			reduce += dicom1.filtreParEnsembleLettre(c1, s);
		}
		
		if(l2.size() > s.size()) {
			reduce += dicom2.filtreParEnsembleLettre(c2, s);
		}
		return reduce;
	}

	/**
	 * Surchage de la methode Equals de la classe Object
	 * @param Un objet avec qui on vérifie l'égalité 
	 * @return un boolean dépendant de la réussite de l'opération
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CroixContrainte other = (CroixContrainte) obj;
		if (c1 != other.c1)
			return false;
		if (c2 != other.c2)
			return false;
		if (m1 != other.m1)
			return false;
		if (m2 != other.m2)
			return false;
		return true;
	}
	
	/**
	 * Surcharge de la methode toString() de la classe Obejct
	 */
	@Override
	public String toString() {
		return "CroixContrainte [m1=" + m1 + ", c1=" + c1 + ", m2=" + m2 + ", c2=" + c2 + "]";
	}

}
