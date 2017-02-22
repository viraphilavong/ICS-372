
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * GUI to implement the Refrigerator
 */
public class GUIDisplay extends RefrigeratorDisplay implements ActionListener {
	private static SimpleDisplay frame;

	/**
	 * Makes it a singleton
	 */
	private GUIDisplay() {
		frame = new SimpleDisplay();
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		initialize();
	}

	/**
	 * This class has most of the widgets
	 *
	 */
	private class SimpleDisplay extends JFrame 
	{
		private JLabel roomTemperature = new JLabel("Room temp");
		private JLabel desiredFridgeTemp = new JLabel("Desired fridge temp");
		private JLabel desiredFreezerTemp = new JLabel("Desired freezer temp");
		private JTextField roomTemperatureInput = new JTextField(10);
		private JTextField desiredFridgeTempInput = new JTextField(10);
		private JTextField desiredFreezerTempInput = new JTextField(10);
		private JButton setRoomTemperature = new JButton("Set room temp");
		private JButton setFridgeTemperature = new JButton("Set desired fridge temp");
		private JButton setFreezerTemperature = new JButton("Set Desired freezer temp");
		private JButton openFridge = new JButton("Open fridge door");
		private JButton closeFridge = new JButton("Close fridge door");
		private JButton openFreezer = new JButton("Open freezer door");
		private JButton closeFreezer = new JButton("Close freezer door");
		private JLabel status = new JLabel("Status");
		private JLabel fridgeLight = new JLabel("Fridge light <on/off>");
		private JLabel freezerLight = new JLabel("Freezer light <on/off>");
		private JLabel fridgeTemp  = new JLabel("Fridge temp <nn>");
		private JLabel freezerTemp = new JLabel("Freezer temp <nn>");
		private JLabel fridgeStatus = new JLabel("Fridge <cooling/idle>");
		private JLabel freezerStatus = new JLabel("Freezer <cooling/idle>");

		/**
		 * Sets up the interface
		 */
		private SimpleDisplay() {
			super("Refrigerator");
			getContentPane().setLayout(null);
			
			final Color DARK_ORANGE = new Color(255, 100, 0);
			final Color PURPLE = new Color(192, 0, 255);
			
			roomTemperature.setForeground(DARK_ORANGE);
			roomTemperature.setBounds(10, 10, 100, 30);
			getContentPane().add(roomTemperature);
			roomTemperatureInput.setBounds(150, 10, 100, 30);
			getContentPane().add(roomTemperatureInput);
			setRoomTemperature.setBounds(255, 10, 150, 30);
			getContentPane().add(setRoomTemperature);
			desiredFridgeTemp.setForeground(DARK_ORANGE);
			desiredFridgeTemp.setBounds(10, 50, 150, 40);
			getContentPane().add(desiredFridgeTemp);
			desiredFridgeTempInput.setBounds(150, 50, 100, 30);
			getContentPane().add(desiredFridgeTempInput);
			setFridgeTemperature.setBounds(255, 50, 170, 30);
			getContentPane().add(setFridgeTemperature);
			desiredFreezerTemp.setForeground(DARK_ORANGE);
			desiredFreezerTemp.setBounds(10, 100, 150, 30);
			getContentPane().add(desiredFreezerTemp);
			desiredFreezerTempInput.setBounds(150, 100, 100, 30);
			getContentPane().add(desiredFreezerTempInput);
			setFreezerTemperature.setBounds(255,100 , 180, 30);
			getContentPane().add(setFreezerTemperature);
			
			openFridge.setBounds(10,200 , 150, 30);
			openFridge.addActionListener(GUIDisplay.this);
			getContentPane().add(openFridge);
			
			closeFridge.setBounds(170,200 , 150, 30);
			closeFridge.addActionListener(GUIDisplay.this);
			getContentPane().add(closeFridge);
			
			openFreezer.setBounds(10,250 , 150, 30);
			openFreezer.addActionListener(GUIDisplay.this);
			getContentPane().add(openFreezer);
			
			closeFreezer.setBounds(170,250 , 150, 30);
			closeFreezer.addActionListener(GUIDisplay.this);
			getContentPane().add(closeFreezer);
			
			status.setForeground(DARK_ORANGE);
			status.setBounds(20, 300, 100, 30);
			getContentPane().add(status);
			
			
			
			fridgeLight.setForeground(PURPLE);
			fridgeLight.setBounds(30, 350, 150, 30);
			getContentPane().add(fridgeLight);
			freezerLight.setForeground(PURPLE);
			
			freezerLight.setBounds(200, 350, 150, 30);
			getContentPane().add(freezerLight);
			fridgeTemp.setForeground(PURPLE);
			
			fridgeTemp.setBounds(30, 400, 150, 30);
			getContentPane().add(fridgeTemp);
			freezerTemp.setForeground(PURPLE);
			freezerTemp.setBounds(200, 400, 150, 30);
			getContentPane().add(freezerTemp);
			fridgeStatus.setForeground(PURPLE);
			fridgeStatus.setBounds(30, 450, 150, 30);
			getContentPane().add(fridgeStatus);
			freezerStatus.setForeground(PURPLE);
			freezerStatus.setBounds(200, 450, 150, 30);
			getContentPane().add(freezerStatus);
			pack();
			setVisible(true);
		}
	}

	/**
	 * Handles the clicks
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		//Close Fridge door
		if (event.getSource().equals(frame.closeFridge)) {
			FridgeContext.instance().processEvent(
					FridgeContext.Events.FRIDGE_DOOR_CLOSED_EVENT);
		
		//Open fridge Door
		} else if (event.getSource().equals(frame.openFridge)) {
			FridgeContext.instance().processEvent(
					FridgeContext.Events.FRIDGE_DOOR_OPENED_EVENT);
		
		//Open freezer
		} else if (event.getSource().equals(frame.openFreezer)) {
			FreezerContext.instance().processEvent(
					FreezerContext.Events.FREEZR_DOOR_OPENED_EVENT);
		}
		//Close Freezer
		else if (event.getSource().equals(frame.closeFreezer)) {
			FreezerContext.instance().processEvent(
						FreezerContext.Events.FREEZR_DOOR_CLOSED_EVENT);
		}
	}

	/**
	 * Indicate that the light is on
	 */
	@Override
	public void turnLightOn(Type type) {
		if(type == RefrigeratorDisplay.Type.FRIDGE)
			frame.fridgeLight.setText("Fridge Light <ON>");
		else
			frame.freezerLight.setText("Freezer Light <ON>");
	}

	/**
	 * Indicate that the light is off
	 */
	@Override
	public void turnLightOff(Type type) {

		if(type == RefrigeratorDisplay.Type.FRIDGE)
			frame.fridgeLight.setText("Fridge Light <OFF>");
		else
			frame.freezerLight.setText("Freezer Light<OFF>");
			
	}

	/**
	 * Indicate that the door is closed
	 */
	@Override
	public void doorClosed(Type type) {
		//TODO;
	}

	/**
	 * Indicate that the door is opened
	 */
	@Override
	public void doorOpened(Type type) {
		//TODO;
	}

	/**
	 * display the remaining time
	 * @param  temp value, Refrigerator type
	 */
	@Override
	public void displayTemperature(int value,Type type) {
		
		if(type == RefrigeratorDisplay.Type.FRIDGE)
			frame.fridgeTemp.setText("Fridge Temp <"+value+">");
		else
			frame.freezerTemp.setText("Freezer Temp <"+value+">");
		
		//TODO ..
	}

	/**
	 * Indicate that it is cooking
	 */
	@Override
	public void compressorOn(Type type) {
		
		if(type == RefrigeratorDisplay.Type.FRIDGE)
			frame.fridgeStatus.setText("Fridge <COOLING>");
		else
			frame.freezerStatus.setText("Freezer <COOLING>");
		
		//TODO ..
	}

	/**
	 * Comperessor off state
	 */
	@Override
	public void compressorOff(Type type) {
		
		if(type == RefrigeratorDisplay.Type.FRIDGE)
			frame.fridgeStatus.setText("Fridge <IDLE>");
		else
			frame.freezerStatus.setText("Freezer <IDLE>");
		
		//TODO ..
	}

	/**
	 * The main method. Creates the interface
	 * @param args  not used
	 */
	public static void main(String[] args) {
		RefrigeratorDisplay display = new GUIDisplay();

	}
}