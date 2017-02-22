import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FiguresPanel extends JPanel{
	Rectangle rectangle;
	Circle circle;
	private int x,y;
	Color color;
	
	public FiguresPanel(){}
	
	public void add(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public void add(Circle circle) {
		this.circle = circle;
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setColor(rectangle.color);
		graphics.fillRect(rectangle.x, rectangle.y, 20, 10);
		graphics.setColor(circle.color);
		graphics.fillOval(circle.x, circle.x, 10, 20);
	}
}