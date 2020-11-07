package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage  extends AbstractClip implements Clip {
	Image image;
	File file;

	public ClipImage(double left, double top, File filename) {
		super(left, top, 0, 0, Color.BLUE);
		file=filename;
		
		image = new Image("file://" +filename.getAbsolutePath());
		super.setGeometry(getLeft(), getTop(), image.getWidth(), image.getHeight());
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, getLeft(), getTop());
	}
	
	@Override
	public Clip copy() {
		return new ClipImage(getLeft(), getTop(), file);
	}
	
}
