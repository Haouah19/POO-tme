package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {
	private Clip clip;
	private double top,left;
	
	// OnMousePressed
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		clip = new ClipEllipse(0, 0, 0, 0, Color.RED);
		left=e.getX();
		top=e.getY();
		
		//to keep the other clips visible on the board
		
	}

	// OnMouseDragged
	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		if (e.getX() > left && e.getY() > top) {
			clip.setGeometry(left, top, e.getX(), e.getY());
		}

		if (e.getX() < left) {
			clip.setGeometry(e.getX(), top, left, e.getY());
		}
		if (e.getY() < top) {
			clip.setGeometry(clip.getLeft(), e.getY(), clip.getRight(), top);
		}
	}
	
	// OnMouseRealeased
	@Override
	public void release(EditorInterface i, MouseEvent e) {
		new CommandAdd(i,clip).execute();
	}

	
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		refrecheScene(i, gc);
		i.getBoard().draw(gc);
//		if (i.getBoard().getContents().contains(clip)) {
//			i.getBoard().draw(gc);
//		}
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Tools Ellipse";
	}

	private void refrecheScene(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0., 0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
		gc.setFill(Color.RED);
		gc.strokeOval(clip.getLeft(), clip.getTop(), clip.getRight()-clip.getLeft(), clip.getBottom()-clip.getTop());
	}
}