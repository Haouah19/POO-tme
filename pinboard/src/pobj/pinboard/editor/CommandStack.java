package pobj.pinboard.editor;
import java.util.Stack;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	
	Stack<Command> undo=new Stack<Command>();
	Stack<Command> redo=new Stack<Command>();
	
	public void addCommand(Command cmd) {
		undo.add(cmd);
		redo.clear();
	}

	public void undo() {
		Command cmd=undo.pop();
		cmd.undo();
		redo.push(cmd);
	}
	
	public void redo() {
		if(!undo.isEmpty()) {
			Command cmd=redo.pop();
			cmd.execute();
			undo.add(cmd);
		}
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();	
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}
