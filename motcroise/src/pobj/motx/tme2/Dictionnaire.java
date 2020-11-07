package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Une liste de mots.
 *
 */
public class Dictionnaire {

	/** ArrayList qui représente la Liste des Mots */
	private List<String> mots = new ArrayList<>();

	/**
	 * Ajoute un mot au Dictionnaire
	 * @param mot à ajouter
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille de la liste est donc du dictionnaire
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	public List<String> getMots(){
		return mots;
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché
	 * @return le mot à cette adresse
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique du Dictionnaire courant
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire du dictionnaire les mots qui ne font pas au minimum "len" de long.
	 * @param len la longueur souhaitée 
	 * @return le nombre de mots retirés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}

	/**
	 * Affiche la liste des mots du dictionnaire 
	 */
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}

	/**
	 * charge un dictionnaire depuis un fichier texte.
	 * @param path nom du fichier
	 * @return Un dictionnaire chargé depuis le fichier path donné en paramétre.
	 */
	public static Dictionnaire loadDictionnaire (String path) {
		Dictionnaire dictionnaire= new Dictionnaire();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) { 
			for (String line = br.readLine() ; line != null ; line = br.readLine() ) { 
				dictionnaire.add(line);
			} 
		} catch (IOException e) { 
		e.printStackTrace();
		}
		
		return dictionnaire;
	}
	

	/**
	 * modifie le dictionnaire pour ne garder que les mots dont la ième lettre est égale au caractère de l’argument c
	 * @param c la lettre supposée 
	 * @param i numéro de la lettre du mot 
	 * @return le nombre de mots qui ont été supprimés du dictionnaire..
	 */
	public int filtreParLettre(char c,int i) {
		int cpt=0;
		List <String> temp= new ArrayList<>();
		for(int j=0;j<mots.size();j++) {
			if(mots.get(j).charAt(i)==c)
				temp.add(mots.get(j));
			else cpt++;
		}
		mots=temp;
		return cpt;
	}
	
	/**
	 * filtrer le dictionnaire par rapport à un indice i et un EnsembleLettre possible lp est de tester pour chaque mot que lp contient bien la lettre d'indice i du mot
	 * @param i indice de lettre supposée être contenue
	 * @param lp EnsembleLettre supposée contenir la lettre d'indice i
	 * @return retourne le nombre de mots
	 */
	public int filtreParEnsembleLettre(int i,EnsembleLettre lp) {
		int cpt=0;
		List <String> temp= new ArrayList<>();
		for(int j=0;j<mots.size();j++) {
			if(lp.contains(mots.get(j).charAt(i)))
				temp.add(mots.get(j));
			else cpt++;
		}
		mots=temp;
		return cpt;
	}
	
	/**
	 * Constuit une liste de Caractéres 
	 * @param c index du caractére de croisement 
	 * @return la liste des caractéres 
	 */
	public EnsembleLettre calculeEnsemble(int i) {
		EnsembleLettre eL = new EnsembleLettre();
		for(String mot : mots) {
			eL.add(mot.charAt(i));
		}
		return eL;
	}
	
	
}
