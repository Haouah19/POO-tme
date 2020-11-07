package pobj.pinboard.editor;


import java.io.File;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
//import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;


public class EditorWindow implements EditorInterface,ClipboardListener{

	private Stage stage;
	// Menu File
	private Menu file = new Menu("File");
	private MenuItem newMenuItem=new MenuItem("New");
	private MenuItem closeMenuItem=new MenuItem("Close");
	
	// Menu edit
	private Menu editMenu = new Menu("Edit");
	private MenuItem copyMenuItem=new MenuItem("Copy");
	private MenuItem pasteMenuItem=new MenuItem("Paste");
	private MenuItem deleteMenuItem=new MenuItem("Delete");
	private MenuItem groupMenuItem=new MenuItem("Group");
	private MenuItem ungroupMenuItem=new MenuItem("Ungroup");
	private MenuItem undoMenuItem=new MenuItem("Undo");
	private MenuItem redoMenuItem=new MenuItem("Redo");
	
	// Menu tools
	private Menu toolsMenu = new Menu("Tools");
	private MenuItem rectangleMenuItem =new MenuItem("Rectangle");
	private MenuItem ellipseMenuItem=new MenuItem("Ellipse");
	

 	
	// Menu bar
	private MenuBar menuBar = new MenuBar(file, editMenu, toolsMenu);
	
	// Buttons & ToolBar
	private Button boxButton = new Button("Box");
	private Button ellipseButton = new Button("Ellipse");
	private Button imageButton = new Button("Img...");
	private Button selectionButton = new Button("Sélection");
	
	private ToolBar toolBar = new ToolBar(boxButton, ellipseButton, imageButton, selectionButton);
	
	private Separator separateur = new Separator();
	
	private Canvas canvas = new Canvas(500, 500);
	private Label status = new Label("");

	private VBox vbox = new VBox();
	private Board board;
	private Scene scene;
	private Tool tool;
	private Selection selection;
	
	private CommandStack stack=new CommandStack();
	//private Clipboard
	public EditorWindow(Stage stage) {

		
		this.stage = stage;
		this.selection = new Selection();
		
		// à revoir 
		tool = new ToolRect();
		
		
		stage.setTitle("PinBoard");
		file.getItems().add(newMenuItem);
		file.getItems().add(closeMenuItem);
		
		toolsMenu.getItems().add(rectangleMenuItem);
		toolsMenu.getItems().add(ellipseMenuItem);
		
		editMenu.getItems().add(copyMenuItem);
		editMenu.getItems().add(pasteMenuItem);
		editMenu.getItems().add(deleteMenuItem);
		editMenu.getItems().add(groupMenuItem);
		editMenu.getItems().add(ungroupMenuItem);
		editMenu.getItems().add(undoMenuItem);
		editMenu.getItems().add(redoMenuItem);

		vbox.getChildren().add(menuBar);
		vbox.getChildren().add(toolBar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(separateur);
		vbox.getChildren().add(status);
		
		
		//Ajout du ClipboardListener dans le Clipboard
		Clipboard.getInstance().addListener(this);
		
		board = new Board();
	
		newMenuItem.setOnAction((e) -> {
			new EditorWindow(new Stage());

		});
		closeMenuItem.setOnAction((e) -> {
			stage.close();
		});
		
		copyMenuItem.setOnAction((e) -> {
			Clipboard.getInstance().clear();
			Clipboard.getInstance().copyToClipboard(selection.getContents());
		});		
		
		pasteMenuItem.setOnAction((e) -> {
			stack.addCommand(new CommandAdd(this, Clipboard.getInstance().copyFromClipboard()));
			clipboardChanged();
			
		});
		
		deleteMenuItem.setOnAction((e) -> {
		for (Clip c: selection.getContents()) {
			board.removeClip(c);
		}
		status.setText("Deleted");
		});
		
		ungroupMenuItem.setOnAction((e)->{
//			for (Clip c : getSelection().getContents()) {
//				if(c instanceof ClipGroup) {
//					getBoard().addClip(((ClipGroup) c).getClips());
//					getBoard().removeClip(c);	
//				}
//			}
		});
		groupMenuItem.setOnAction((e)->{
			stack.addCommand(new CommandGroup(this, selection.getContents()));
			this.clipboardChanged();
		});

//		groupMenuItem.setOnAction((e) -> {
//			ClipGroup cg=new ClipGroup();
//		for (Clip c: selection.getContents()) {
//			cg.addClip(c);
//			board.removeClip(c);
//		}	
//		board.addClip(cg);
//		status.setText("Grouped");
//	});
		
		undoMenuItem.setOnAction((e)->{
			stack.undo();
			getBoard().draw(canvas.getGraphicsContext2D());
			this.clipboardChanged();
		});
	
		redoMenuItem.setOnAction((e)->{
			stack.redo();
			getBoard().draw(canvas.getGraphicsContext2D());
			this.clipboardChanged();		
		});
		
		canvas.setOnMousePressed((e) -> {
			tool.press(this, e);
			tool.drawFeedback(this,canvas.getGraphicsContext2D());

		});
		canvas.setOnMouseDragged((e) -> {
			tool.drag(this, e);
			tool.drawFeedback(this,canvas.getGraphicsContext2D());

		});
		canvas.setOnMouseReleased((e) -> {
			tool.release(this, e);
			tool.drawFeedback(this,canvas.getGraphicsContext2D());
		});

		boxButton.setOnAction((e)->{
			tool=new ToolRect();
			status=new Label(tool.getName(this));
		});
		
		ellipseButton.setOnAction((e)->{
			tool=new ToolEllipse();
			status=new Label(tool.getName(this));
		});
		
		selectionButton.setOnAction((e)->{
			tool=new ToolSelection();
			status=new Label(tool.getName(this));
		});
		
		rectangleMenuItem.setOnAction((e)->{
			tool=new ToolRect();
			status=new Label(tool.getName(this));
		});
		
		ellipseMenuItem.setOnAction((e)->{
			tool = new ToolEllipse();
			status=new Label(tool.getName(this));
		});

		scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
		
	}

	public Stage getStage() {
		return stage;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selection;
	}

	@Override
	public CommandStack getUndoStack() {
		return stack;
	}

	@Override
	public void clipboardChanged() {
		if(Clipboard.getInstance().isEmpty())
			pasteMenuItem.setDisable(true);
		else
			pasteMenuItem.setDisable(false);
		
		if(stack.isUndoEmpty())
			undoMenuItem.setDisable(true);
		else
			undoMenuItem.setDisable(false);
		
		if(stack.isRedoEmpty())
			redoMenuItem.setDisable(true);
		else
			redoMenuItem.setDisable(false);
		
	}

}