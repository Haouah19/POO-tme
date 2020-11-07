package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolSelection implements Tool{
 
	private double x,y;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		if(!e.isShiftDown()) {
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		}else {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		}
		i.getBoard().getContents().removeAll(i.getSelection().getContents());
		x= e.getX();
		y= e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for(Clip c : i.getSelection().getContents()) {
			c.move(e.getX()-x, e.getY()-y);
		}
		x= e.getX();
		y = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		new CommandAdd(i,i.getSelection().getContents()).execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
		i.getBoard().draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "tools Sélection";
	}
	

}
