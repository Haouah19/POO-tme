package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {

	private List<Clip> myList;
	private EditorInterface editorinterface;
	private Clip old;
	private List<Clip> oldL;
	private int choice;

	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.myList = new ArrayList<>();
		this.editorinterface = editor;
		myList.add(toAdd);
		old = toAdd;
		choice = 0;
	}

	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editorinterface = editor;
		myList = toAdd;
		oldL = toAdd;
		choice = 1;
	}

	@Override
	public void execute() {
		if (choice == 0)
			this.editorinterface.getBoard().addClip(old);
		else
			this.editorinterface.getBoard().addClip(oldL);
	}

	@Override
	public void undo() {
		if (choice == 0)
			this.editorinterface.getBoard().removeClip(old);
		else
			this.editorinterface.getBoard().removeClip(oldL);
	}

}