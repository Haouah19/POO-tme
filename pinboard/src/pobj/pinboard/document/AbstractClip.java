package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip {

	protected double top;
	protected double left;
	protected double right;
	protected double bottom;
	protected Color c;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left=left;
		this.bottom=bottom;
		this.top=top;
		this.right=right;
		this.c=color;
	}
	
	public AbstractClip() {
		c=Color.WHITE;
	}

	public double getTop() {
		return top;
	}

	public double getLeft() {
		return left;
	}

	public double getBottom() {
		return bottom;
	}

	public double getRight() {
		return right;
	}

	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.top=top;
		this.right=right;
		this.bottom=bottom;
	}
	public void setColor(Color c) {
		this.c=c;
	}

	public boolean isSelected(double x, double y) {
		return x>=left&& x<=right&& y<=bottom&& y>=top;
	}
	public Color getColor() {
		// TODO Auto-generated method stub
		return c;
	}
	public void move(double x, double y) {
		this.left+=x;
		this.right+=x;
		this.bottom+=y;
		this.top+=y;
	}

	public abstract void draw(GraphicsContext ctx);
	public abstract Clip copy(); 
}
