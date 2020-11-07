package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

/**
 * presse-papiers
 * @author AmineD
 *
 */
public class Clipboard {

	/**
	 *  unique presse-papiers, créé au chargement de la classe
	 */
	private static Clipboard clipboard = new Clipboard();
	
	
	/**
	 *  liste des éléments copiés
	 */
	private List<Clip> copied;
	
	/**
	 *  liste des clipbordListeners (cibles)
	 */
	private List<ClipboardListener> clipboardListeners;
	
	//privé afin d’empêcher tout autre classe de créer par erreur un presse-papiers
	private Clipboard() {
		copied = new ArrayList<Clip>();
		clipboardListeners=new ArrayList<>();
	}
	
	/**
	 * copie les éléments graphiques en argument dans le presse-papiers.
	 * @param clips
	 */
	public void copyToClipboard(List<Clip> clips){
		
		for(Clip c:clips) 
			copied.add(c.copy());
	}
	
	/**
	 * retourne une copie des éléments graphiques du presse-papiers
	 * @return
	 */
	public List<Clip> copyFromClipboard() {
		List<Clip> tempCopied= new ArrayList<>();
		for(Clip c:copied) 
			tempCopied.add(c.copy());
		notifyListeners();
		return tempCopied;
	}
	
	/**
	 * vide le contenu du presse-papiers.
	 */
	public void clear() {
		copied.clear();
		notifyListeners();
	}
	
	/**
	 * indique si le presse-papiers est vide
	 * @return
	 */
	public boolean isEmpty() {
		return copied.isEmpty();
	}
	
	/**
	 * retourne l’attribut Clipboard statique
	 * @return
	 */
	public static Clipboard getInstance() {
		return clipboard;
	}
	
	/**
	 * permet l'ajout d'un ClipboardListener
	 * @param clipboardListener
	 */
	public void addListener(ClipboardListener clipboardListener) {
		clipboardListeners.add(clipboardListener);
		notifyListeners();
	}
	
	/**
	 * permet la suppression d'un ClipboardListener
	 * @param clipboardListener
	 */
	public void removeListener(ClipboardListener clipboardListener) {
		clipboardListeners.remove(clipboardListener);
		notifyListeners();
	}
	
	/**
	 * permet de notifier les clipboardListener cibles
	 */
	public void notifyListeners() {
		for (ClipboardListener cl: clipboardListeners) {
			cl.clipboardChanged();
		}
	}
	
	/**
	 * retourne les clipboardListener cibles
	 * @return
	 */
	public List<ClipboardListener> getClipboardListeners() {
		return clipboardListeners;
	}
	
}
