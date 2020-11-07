package pobj.pinboard.editor.commands;

import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {
	
	private EditorInterface editorinterface;
	ClipGroup clipgroup;
	
	public CommandUngroup(EditorInterface editor, ClipGroup group) {
		this.clipgroup=group;
		this.editorinterface=editor;

	}

	@Override
	public void execute() {
	
		editorinterface.getBoard().getContents().remove(clipgroup);
		editorinterface.getBoard().getContents().addAll(clipgroup.getClips());
	}

	@Override
	public void undo() {
	
		editorinterface.getBoard().getContents().removeAll(clipgroup.getClips());
		editorinterface.getBoard().getContents().add(clipgroup);
	}

}