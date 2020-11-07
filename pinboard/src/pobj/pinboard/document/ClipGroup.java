package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite{

	private List<Clip> clips=new ArrayList<>();
	
	/**
	 * Coordonnées de tous les clips
	 */
	private List<Double> leftC = new ArrayList<Double>();
	private List<Double> rightC = new ArrayList<Double>();
	private List<Double> topC = new ArrayList<Double>();
	private List<Double> bottomC = new ArrayList<Double>();
	

	
	
	public ClipGroup(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
		
	}

	public ClipGroup() {
		super();
		left=Double.MIN_VALUE;
		top=Double.MIN_VALUE;
		right=Double.MAX_VALUE;
		bottom=Double.MAX_VALUE;
		
	}

	/**
	 * retourne la liste des clips du ClipGroup
	 */
	@Override
	public List<Clip> getClips() {
		return clips;
	}

	/**
	 * ajoute un clip au ClipGroup
	 */
	@Override
	public void addClip(Clip toAdd) {
		clips.add(toAdd);
		
		//Ajoute les coordonnées du clip de la liste des coordonnées
		leftC.add(toAdd.getLeft());
		rightC.add(toAdd.getRight());
		topC.add(toAdd.getTop());
		bottomC.add(toAdd.getBottom());
		
		updateRectangleCoord();
		
	}

	/**
	 * Supprime un Clip du ClipGroup
	 */
	@Override
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);
		
		//supprimme les coordonnées du clip de la liste des coordonnées
		leftC.remove(toRemove.getLeft());
		rightC.remove(toRemove.getRight());
		topC.remove(toRemove.getTop());
		bottomC.remove(toRemove.getBottom());
		
		updateRectangleCoord();
		
	}
	
	/**
	 * deplace le groupe
	 */
	public void move(double x, double y) {
		updateRectangleCoord();
		for (Clip c : clips) {
			c.move(x, y);
		}
		left+=x;
		right+=x;
		bottom+=y;
		top+=y;
	}
	

	/*
	 * Dessine le ClipGroup
	 * @see pobj.pinboard.document.AbstractClip#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.strokeRect(left	, top, left-right,bottom-top);
		for (Clip c : clips){
			move(right-left,bottom-top);
			c.draw(ctx);
		}
		
	}
	
	/**
	 * Crée une nouvelle copie du ClipGroupe
	 */
	@Override
	public Clip copy() {
		ClipGroup copy = new ClipGroup(getLeft(),getTop(),getRight(),getBottom(),getColor());
		for ( Clip c : clips){
			copy.addClip(c.copy());
		}
		return copy;
	}
	
	/**
	 * met à jour les coordonnées du rectangle englobant
	 */
	public void updateRectangleCoord() {
		
		if(clips.isEmpty()) {
			left=Double.MIN_VALUE;
			top=Double.MIN_VALUE;
			right=Double.MAX_VALUE;
			bottom=Double.MAX_VALUE;
		}else {
			
		left = Collections.min(leftC);
		top = Collections.min(topC);
		right = Collections.max(rightC);
		bottom = Collections.max(bottomC);
		}
	}
}
