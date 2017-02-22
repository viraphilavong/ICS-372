import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Circle extends JPanel {
	public int x;
	public Color color;
	
	public Circle(int x, Color color) {
		this.x = x;
		this.color = color;
	}

}
