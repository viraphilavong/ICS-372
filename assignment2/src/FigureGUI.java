import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class FigureGUI extends JFrame {
	
    public boolean rcheck, bcheck, gcheck, rectcheck, circcheck;
    private Color color;
    private int x,y;
    private Container panel;
    private JButton redButton = new JButton("Red");
    private JButton greenButton = new JButton("Green");
    private JButton blueButton = new JButton("Blue");
    private JButton rectangleButton = new JButton("Rectangle");
    private JButton circleButton = new JButton("Circle");
    private JButton exitButton = new JButton("Exit");
    private JTextArea listArea = new JTextArea(10, 10);
    ButtonHandler buttonHandler = new ButtonHandler();
    FiguresPanel figurePanel = new FiguresPanel();
    private JLabel date;
    
    public FigureGUI() {
    	
        super("Assignment 2");
        panel = getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel(), BorderLayout.CENTER);
        panel.add(listPanel(), BorderLayout.EAST);
        panel.add(mainPanel(), BorderLayout.WEST);
        
    }
    
    public JPanel mainPanel(){ 
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.setPreferredSize(new Dimension(100,100));
    	
    	mainPanel.add(figurePanel, BorderLayout.CENTER);    	
    	MouseHandler mouseHandler = new MouseHandler();
    	figurePanel.addMouseListener(mouseHandler);
    	figurePanel.addMouseMotionListener(mouseHandler);
    	
    	SimpleDateFormat day = new SimpleDateFormat("MM/dd/yyyy");
    	date = new JLabel(day.toString());
    	mainPanel.add(date, BorderLayout.SOUTH);
    	
    	return mainPanel;
            
    }
    
    public JPanel buttonPanel(){  
    	
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(2,3));
    	buttonPanel.add(redButton);
    	buttonPanel.add(greenButton);
    	buttonPanel.add(blueButton);
    	buttonPanel.add(rectangleButton);
    	buttonPanel.add(circleButton);
    	buttonPanel.add(exitButton);
    	redButton.addActionListener(buttonHandler);
    	greenButton.addActionListener(buttonHandler);
    	blueButton.addActionListener(buttonHandler);
    	rectangleButton.addActionListener(buttonHandler);
    	circleButton.addActionListener(buttonHandler);
    	exitButton.addActionListener(buttonHandler);
            
    	return buttonPanel;
    }
    
    public JPanel listPanel(){
    	
    	JPanel listPanel = new JPanel();    	
    	listArea.setLineWrap(true);
    	listPanel.add(listArea);      
        
    	return listPanel;
    }
    
    private class ButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
    	Object click = e.getSource();
    	if (click == redButton) {
    		color = Color.RED;
        } else if (click == greenButton) {
        	color = Color.GREEN;
        } else if (click == blueButton) {
        	color = Color.BLUE;
        } else if (click == rectangleButton) {
        	rectcheck = true;
        	circcheck = false;
        } else if (click == circleButton) {
        	rectcheck = false;
        	circcheck = true;
        } else if (click == exitButton) {
            System.exit(0);  
        }
    }
    }
    
    private class MouseHandler extends MouseAdapter {
    	@Override
    	public void mousePressed(MouseEvent e) {
    		listArea.append(String.format("Clicked here (%d,%d)\n", e.getX(), e.getY()));
    		x = e.getX();
    		y = e.getY();
    		drawShape();
    	}
    }
    public void drawShape(){
    	if(rectcheck == true){
    		Rectangle rectangle = new Rectangle(x,y,10, 20, color);
    		figurePanel.add(rectangle);
    		figurePanel.repaint();
    		
    	} else if (circcheck == true) {
    		Circle circle = new Circle(20,color);
    		figurePanel.add(circle);
    		figurePanel.repaint();    		
    	}
    }
    
    
    public static void main(String[] args) {
       FigureGUI figure = new FigureGUI();      
       figure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       figure.pack();
       figure.setVisible(true);
    }
}