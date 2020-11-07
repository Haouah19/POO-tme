package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> myList = new ArrayList<>();
	
	public void select(Board board, double x, double y) {
		clear();
		for( Clip clip : board.getContents()) {
			if(clip.isSelected(x, y)) {
				myList.add(clip);
				break;
			}
		}
	}
	public void toogleSelect(Board board, double x, double y) {
		for( Clip clip : board.getContents()) {
			if(clip.isSelected(x, y)) {
				if(myList.contains(clip)) {
					myList.remove(clip);
				}else {
					myList.add(clip);
				}	
			}
		}
	}
	
	public void clear() {
		myList.clear();
	}
	
	public List<Clip> getContents(){
		return myList;
	}
	public void drawFeedback(GraphicsContext gc) {
		refrecheScene(gc);
		for(Clip clip : myList) {
			clip.draw(gc);	
			double l = clip.getLeft();
			double t = clip.getTop();
			double width = clip.getRight()-l;
			double heigth =clip.getBottom() - t;
			
			gc.strokeRect(l, t, width, heigth);
		}
	}
	
	private void refrecheScene(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0., 0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
	}
}
