package pobj.pinboard.editor.commands;

import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command{

	private EditorInterface editorinterface;
	private ClipRect clipreact;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor, ClipRect rect, double d, double e) {
		this.editorinterface=editor;
		this.x=d;
		this.y=e;
		this.clipreact=rect;
		
	}

	@Override
	public void execute() {
		editorinterface.getBoard().removeClip(clipreact);
		clipreact.move(x, y);
		editorinterface.getBoard().addClip(clipreact);
	}

	@Override
	public void undo() {
		editorinterface.getBoard().removeClip(clipreact);
		clipreact.move(-x, -y);
		editorinterface.getBoard().addClip(clipreact);
		
	}

}