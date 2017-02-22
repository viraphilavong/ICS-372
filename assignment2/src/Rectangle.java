import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Rectangle extends JPanel {
	
	private final int width, length;
	public int x,y;
	public Color color;
	
	public Rectangle(int x, int y, int width, int length, Color color) {
		this.x=x;
		this.y=y;
		this.width = width;
		this.length = length;
		this.color = color;
	}
}
