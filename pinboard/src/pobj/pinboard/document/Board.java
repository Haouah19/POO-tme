package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private List<Clip> myList ;

	public Board() {
		this.myList = new ArrayList<>();
	}
	
	public List<Clip> getContents(){
		return myList;
	}
	public void addClip(Clip clip) {
		myList.add(clip);
	}
	public void addClip(List<Clip> clip) {
		myList.addAll(clip);
	}
	
	public void removeClip(Clip clip) {

			myList.remove(clip);
		
	}
	
	
	public void removeClip(List<Clip> clip) {
		
		myList.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,gc.getCanvas().getWidth(),0,gc.getCanvas().getHeight());
		for (Clip c: myList)
			c.draw(gc);
	}
}
