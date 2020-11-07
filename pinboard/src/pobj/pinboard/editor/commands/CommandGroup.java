package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{

	private List<Clip> myList;
	private EditorInterface editorinterface ;
	ClipGroup clipgroup;
	
	public CommandGroup(EditorInterface editor, List<Clip> rects) {
		this.myList = new ArrayList<>();
		this.editorinterface=editor;
		this.myList= rects;
		clipgroup =new ClipGroup();
		
		for (Clip t:myList) {
			clipgroup.addClip(t);
			}
		}

	@Override
	public void execute() {
		editorinterface.getBoard().removeClip(myList);
		editorinterface.getBoard().addClip(clipgroup);
	}

	@Override
	public void undo() {
		editorinterface.getBoard().removeClip(clipgroup);
		editorinterface.getBoard().addClip(clipgroup.getClips());
	}

}
